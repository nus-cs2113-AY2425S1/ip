package archibald;

import archibald.task.Task;
import archibald.task.Todo;
import archibald.task.Deadline;
import archibald.task.Event;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Main class for the Archibald task management application.
 */
public class Archibald {
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;
    private static Parser parser;

    /**
     * The main method that runs the Archibald application.
     * 
     * @param args Command line arguments (not used)
     */
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

    /**
     * Loads tasks from storage.
     */
    private static void loadTasks() {
        try {
            tasks = storage.load();
        } catch (ArchibaldException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }
}

/**
 * Handles user interface operations for the Archibald application.
 */
class Ui {
    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        printArchibaldResponse("Hello, I am known as Archibald,\nhow may I be of assistance!");
    }

    /**
     * Displays an error message to the user.
     * 
     * @param message The error message to display
     */
    public void showError(String message) {
        printArchibaldResponse("Error: " + message);
    }

    /**
     * Prints a response from Archibald with formatting.
     * 
     * @param message The message to print
     */
    public void printArchibaldResponse(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}

/**
 * Handles storage operations for the Archibald application.
 */
class Storage {
    private String filePath;
    private static final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a Storage object with the specifed file path.
     * 
     * @param filePath The path to the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * 
     * @return A TaskList containing the loaded tasks
     * @throws ArchibaldException If there's an error loading the tasks
     */
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

    /**
     * Saves the given tasks to the storage file.
     * 
     * @param tasks The TaskList to save
     * @throws ArchibaldException If there's an error saving the tasks
     */
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

/**
 * Parses user input into commands for the Archibald application.
 */
class Parser {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_HAPPENING = "happening";
    private static final String COMMAND_FIND = "find";
    private static final int COMMAND_PARTS = 2;

    /**
     * Parses the user input into a Command object.
     * 
     * @param userInput The user's input string
     * @return A Command object based on the user's input
     * @throws ArchibaldException If the input cannot be parsed into a valid command
     */
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
        case COMMAND_HAPPENING:
            return new HappeningCommand(parts[1]);
        case COMMAND_FIND:
            return new FindCommand(parts[1]);
        default:
            return new AddCommand(userInput);
        }
    }
}

/**
 * Represents a list of tasks in the Archibald application.
 */
class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     * 
     * @param index The index of the task to delete
     * @throws ArchibaldException If the index is invalid
     */
    public void deleteTask(int index) throws ArchibaldException {
        if (index < 0 || index >= tasks.size()) {
            throw new ArchibaldException("Invalid task number.");
        }
        tasks.remove(index);
    }

    /**
     * Gets a task from the list at the specified index.
     * 
     * @param index The index of the task to get
     * @return The task at the specified index
     * @throws ArchibaldException If the index is invalid
     */
    public Task getTask(int index) throws ArchibaldException {
        if (index < 0 || index >= tasks.size()) {
            throw new ArchibaldException("Invalid task number.");
        }
        return tasks.get(index);
    }

    /**
     * Gets a copy of the list of tasks.
     * 
     * @return A new ArrayList containing all tasks
     */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Gets the number of tasks in the list.
     * 
     * @return The number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     * 
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Finds tasks that occur on a specific date.
     * 
     * @param date The date to search for
     * @return A list of tasks occurring on the specified date
     */
    public List<Task> findTasksOnDate(LocalDate date) {
        List<Task> tasksOnDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().toLocalDate().equals(date)) {
                    tasksOnDate.add(task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getFrom().toLocalDate().equals(date) ||
                    event.getTo().toLocalDate().equals(date)) {
                    tasksOnDate.add(task);
                }
            }
        }
        return tasksOnDate;
    }

    /**
     * Finds tasks that contain a specific keyword in their description.
     * 
     * @param keyword The keyword to search for
     * @return A list of tasks containing the keyword
     */
    public List<Task> findTasksByKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}

/**
 * Abstract base class for all commands in the Archibald application.
 */
abstract class Command {
    /**
     * Executes the command.
     * 
     * @param tasks The TaskList to operate on
     * @param ui The Ui to use for output
     * @param storage The Storage to use for saving tasks
     * @throws ArchibaldException If there's an error executing the command
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ArchibaldException;
}

/**
 * Command for adding a new task to the list.
 */
class AddCommand extends Command {
    private static final String TODO_TYPE = "todo";
    private static final String DEADLINE_TYPE = "deadline";
    private static final String EVENT_TYPE = "event";
    private static final String DEADLINE_SEPARATOR = "/by";
    private static final String EVENT_FROM_SEPARATOR = "/from";
    private static final String EVENT_TO_SEPARATOR = "/to";
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private String fullCommand;

    /**
     * Constructs an AddCommand with the given full command string.
     * 
     * @param fullCommand The full command string for adding a task
     */
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

    /**
     * Creates a new Task object based on the input string.
     * 
     * @param input The input string describing the task
     * @return A new Task object
     * @throws ArchibaldException If the input is invalid or cannot be parsed
     */
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

/**
 * Command for listing all tasks.
 */
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

/**
 * Command for marking a task as done.
 */
class MarkCommand extends Command {
    private String taskNumber;

    /**
     * Constructs a MarkCommand for the specified task number.
     * 
     * @param taskNumber The number of the task to mark as done
     */
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

/**
 * Command for unmarking a task (marking it as not done).
 */
class UnmarkCommand extends Command {
    private String taskNumber;

    /**
     * Constructs an UnmarkCommand for the specified task number.
     * 
     * @param taskNumber The number of the task to unmark
     */
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

/**
 * Command for deleting a task from the list.
 */
class DeleteCommand extends Command {
    private String taskNumber;

    /**
     * Constructs a DeleteCommand for the specified task number.
     * 
     * @param taskNumber The number of the task to delete
     */
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

/**
 * Command for exiting the application.
 */
class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArchibaldException {
        ui.printArchibaldResponse("I bid thee farewell! May our paths cross again!");
        storage.save(tasks);
    }
}

/**
 * Command for finding tasks happening on a specific date.
 */
class HappeningCommand extends Command {
    private static final DateTimeFormatter HAPPENING_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String dateString;

    /**
     * Constructs a HappeningCommand for the specifid date.
     * 
     * @param dateString The date to search for tasks
     */
    public HappeningCommand(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArchibaldException {
        try {
            LocalDate date = LocalDate.parse(dateString, HAPPENING_DATE_FORMAT);
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

/**
 * Command for finding tasks containing a specific keyword.
 */
class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand for the specified keyword.
     * 
     * @param keyword The keyword to search for in tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ArchibaldException {
        List<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            ui.printArchibaldResponse("No tasks found containing '" + keyword + "'");
        } else {
            StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            ui.printArchibaldResponse(response.toString().trim());
        }
    }
}

/**
 * Custom exception class for Archibald-specific errors.
 */
class ArchibaldException extends Exception {
    /**
     * Constructs an ArchibaldException with the specifed error message
     * 
     * @param message The error message
     */
    public ArchibaldException(String message) {
        super(message);
    }
}