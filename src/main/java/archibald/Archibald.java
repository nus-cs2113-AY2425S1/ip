package archibald;

import archibald.task.Task;
import archibald.task.Todo;
import archibald.task.Deadline;
import archibald.task.Event;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Archibald {
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;
    private static Parser parser;

    public static void main(String[] args) {
        ui = new Ui();
        storage = new Storage("./data/archibald.txt");
        tasks = new TaskList();
        parser = new Parser();

        ui.showWelcome();
        loadTasks();

        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            String fullCommand = scanner.nextLine();
            try {
                Command c = parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = (c instanceof ExitCommand);
            } catch (ArchibaldException e) {
                ui.showError(e.getMessage());
            }
        }

        scanner.close();
    }

    private static void loadTasks() {
        try {
            tasks = storage.load();
        } catch (ArchibaldException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }
}

class Ui {
    public void showWelcome() {
        printArchibaldResponse("Hello, I am known as Archibald,\nhow may I be of assistance!");
    }

    public void showError(String message) {
        printArchibaldResponse("Error: " + message);
    }

    public void printArchibaldResponse(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}

class Storage {
    private String filePath;
    private static final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws ArchibaldException {
        TaskList loadedTasks = new TaskList();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                Files.createDirectories(Paths.get(filePath).getParent());
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                Task task;
                switch (type) {
                case "T":
                    task = new Todo(parts[2]);
                    break;
                case "D":
                    LocalDateTime deadlineDate = LocalDateTime.parse(parts[3], FILE_DATE_FORMAT);
                    task = new Deadline(parts[2], deadlineDate);
                    break;
                case "E":
                    LocalDateTime eventStartDate = LocalDateTime.parse(parts[3], FILE_DATE_FORMAT);
                    LocalDateTime eventEndDate = LocalDateTime.parse(parts[4], FILE_DATE_FORMAT);
                    task = new Event(parts[2], eventStartDate, eventEndDate);
                    break;
                default:
                    continue;
                }
                if (isDone) {
                    task.markAsDone();
                }
                loadedTasks.addTask(task);
            }
            reader.close();
        } catch (IOException e) {
            throw new ArchibaldException("Unable to load tasks from file.");
        }
        return loadedTasks;
    }

    public void save(TaskList tasks) throws ArchibaldException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks.getTasks()) {
                writer.println(task.toSaveFormat());
            }
        } catch (IOException e) {
            throw new ArchibaldException("Unable to save tasks to file.");
        }
    }
}

class Parser {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_FIND = "find";
    private static final int COMMAND_PARTS = 2;

    public Command parseCommand(String userInput) throws ArchibaldException {
        String[] parts = userInput.split(" ", COMMAND_PARTS);
        String commandType = parts[0].toLowerCase();

        switch (commandType) {
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_MARK:
            return new MarkCommand(parts[1]);
        case COMMAND_UNMARK:
            return new UnmarkCommand(parts[1]);
        case COMMAND_DELETE:
            return new DeleteCommand(parts[1]);
        case COMMAND_BYE:
            return new ExitCommand();
        case COMMAND_FIND:
            return new FindCommand(parts[1]);
        default:
            return new AddCommand(userInput);
        }
    }
}

class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws ArchibaldException {
        if (index < 0 || index >= tasks.size()) {
            throw new ArchibaldException("Invalid task number.");
        }
        tasks.remove(index);
    }

    public Task getTask(int index) throws ArchibaldException {
        if (index < 0 || index >= tasks.size()) {
            throw new ArchibaldException("Invalid task number.");
        }
        return tasks.get(index);
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public List<Task> findTasksOnDate(LocalDateTime date) {
        List<Task> tasksOnDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().toLocalDate().equals(date.toLocalDate())) {
                    tasksOnDate.add(task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getFrom().toLocalDate().equals(date.toLocalDate()) ||
                    event.getTo().toLocalDate().equals(date.toLocalDate())) {
                    tasksOnDate.add(task);
                }
            }
        }
        return tasksOnDate;
    }
}

abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ArchibaldException;
}

class AddCommand extends Command {
    private static final String TODO_TYPE = "todo";
    private static final String DEADLINE_TYPE = "deadline";
    private static final String EVENT_TYPE = "event";
    private static final String DEADLINE_SEPARATOR = "/by";
    private static final String EVENT_FROM_SEPARATOR = "/from";
    private static final String EVENT_TO_SEPARATOR = "/to";
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private String fullCommand;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArchibaldException {
        Task newTask = createTask(fullCommand);
        tasks.addTask(newTask);
        ui.printArchibaldResponse("Got it. I've added this task:\n  " + newTask
                + "\nNow you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }

    private Task createTask(String input) throws ArchibaldException {
        String[] parts = input.split(" ", 2);
        String type = parts[0].toLowerCase();
        if (!type.equals(TODO_TYPE) && !type.equals(DEADLINE_TYPE) && !type.equals(EVENT_TYPE)) {
            throw new ArchibaldException("I don't know what that means");
        }
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new ArchibaldException("By royal decree, the description of a " + type + " cannot be empty.");
        }
        switch (type) {
        case TODO_TYPE:
            return new Todo(parts[1]);
        case DEADLINE_TYPE:
            String[] deadlineParts = parts[1].split(" " + DEADLINE_SEPARATOR + " ", 2);
            if (deadlineParts.length < 2) {
                throw new ArchibaldException("Deadline must include '" + DEADLINE_SEPARATOR + "' followed by a date and time.");
            }
            try {
                LocalDateTime deadlineDate = LocalDateTime.parse(deadlineParts[1], INPUT_DATE_FORMAT);
                return new Deadline(deadlineParts[0], deadlineDate);
            } catch (DateTimeParseException e) {
                throw new ArchibaldException("Invalid date format. Please use yyyy-MM-dd HHmm");
            }
        case EVENT_TYPE:
            String[] eventParts = parts[1].split(" " + EVENT_FROM_SEPARATOR + " | " + EVENT_TO_SEPARATOR + " ");
            if (eventParts.length < 3) {
                throw new ArchibaldException("Event must include '" + EVENT_FROM_SEPARATOR + "' and '"
                        + EVENT_TO_SEPARATOR + "' followed by dates and times.");
            }
            try {
                LocalDateTime eventStartDate = LocalDateTime.parse(eventParts[1], INPUT_DATE_FORMAT);
                LocalDateTime eventEndDate = LocalDateTime.parse(eventParts[2], INPUT_DATE_FORMAT);
                return new Event(eventParts[0], eventStartDate, eventEndDate);
            } catch (DateTimeParseException e) {
                throw new ArchibaldException("Invalid date format. Please use yyyy-MM-dd HHmm");
            }
        default:
            throw new ArchibaldException("In spite of my knowledge... I don't know what that means >:(");
        }
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printArchibaldResponse("The task list is currently empty.");
            return;
        }
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        List<Task> taskList = tasks.getTasks();
        for (int i = 0; i < taskList.size(); i++) {
            response.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        ui.printArchibaldResponse(response.toString().trim());
    }
}

class MarkCommand extends Command {
    private String taskNumber;

    public MarkCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArchibaldException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            Task task = tasks.getTask(index);
            task.markAsDone();
            ui.printArchibaldResponse("Great Wizard of Skibidiness! Thou hath completed task:\n  " + task);
            storage.save(tasks);
        } catch (NumberFormatException e) {
            throw new ArchibaldException("Invalid task number format.");
        }
    }
}

class UnmarkCommand extends Command {
    private String taskNumber;

    public UnmarkCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArchibaldException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            Task task = tasks.getTask(index);
            task.markAsNotDone();
            ui.printArchibaldResponse("Big yikers! The task hath been marked undone:\n  " + task);
            storage.save(tasks);
        } catch (NumberFormatException e) {
            throw new ArchibaldException("Invalid task number format.");
        }
    }
}

class DeleteCommand extends Command {
    private String taskNumber;

    public DeleteCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArchibaldException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            Task removedTask = tasks.getTask(index);
            tasks.deleteTask(index);
            ui.printArchibaldResponse("Duly noted sire. Thy hath removed this task:\n  " 
                    + removedTask + "\nNow thou hath " + tasks.size() 
                    + " tasks in the list.");
            storage.save(tasks);
        } catch (NumberFormatException e) {
            throw new ArchibaldException("Invalid task number format.");
        }
    }
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArchibaldException {
        ui.printArchibaldResponse("I bid thee farewell! May our paths cross again!");
        storage.save(tasks);
    }
}

class FindCommand extends Command {
    private static final DateTimeFormatter FIND_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String dateString;

    public FindCommand(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArchibaldException {
        try {
            LocalDateTime date = LocalDateTime.parse(dateString, FIND_DATE_FORMAT);
            List<Task> tasksOnDate = tasks.findTasksOnDate(date);
            if (tasksOnDate.isEmpty()) {
                ui.printArchibaldResponse("No tasks found on " + dateString);
            } else {
                StringBuilder response = new StringBuilder("Tasks on " + dateString + ":\n");
                for (int i = 0; i < tasksOnDate.size(); i++) {
                    response.append(i + 1).append(". ").append(tasksOnDate.get(i)).append("\n");
                }
                ui.printArchibaldResponse(response.toString().trim());
            }
        } catch (DateTimeParseException e) {
            throw new ArchibaldException("Invalid date format. Please use yyyy-MM-dd");
        }
    }
}

class ArchibaldException extends Exception {
    public ArchibaldException(String message) {
        super(message);
    }
}
