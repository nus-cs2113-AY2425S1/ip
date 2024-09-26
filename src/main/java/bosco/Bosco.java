package bosco;

import bosco.ui.Ui;

import bosco.task.TaskList;
import bosco.task.Task;
import bosco.task.Todo;
import bosco.task.Deadline;
import bosco.task.Event;

import bosco.exception.IllegalCommandException;
import bosco.exception.EmptyDescriptionException;
import bosco.exception.MissingPrefixException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class Bosco {
    private static final String FILE_PATH = "./data/bosco.txt";
    private static final String DIVIDER =
            "\t________________________________________________________________________________";
    private static final String INDENT_START = "\t ";
    private static final String INDENT_EXTRA = "  ";
    private static final String MESSAGE_MARK_DONE = "Nice! I've marked this task as done:";
    private static final String MESSAGE_MARK_UNDONE = "OK, I've marked this task as not done yet:";
    private static final String MESSAGE_ADDED_TASK = "Got it. I've added this task:";
    private static final String MESSAGE_DELETED_TASK = "Noted. I've removed this task:";
    private static final String DEADLINE_PREFIX_BY = "/by";
    private static final String EVENT_PREFIX_FROM = "/from";
    private static final String EVENT_PREFIX_TO = "/to";

    private static Ui ui;
    private static TaskList tasks;

    private static void loadFileContents(String filePath) throws IOException {
        Path inputPath = createFilePathIfNotExists(filePath);
        Scanner fileScanner = new Scanner(inputPath);
        while (fileScanner.hasNext()) {
            addTaskFromFileLine(fileScanner.nextLine());
        }
    }

    private static Path createFilePathIfNotExists(String filePath) {
        Path p = Paths.get(filePath);
        try {
            Files.createDirectories(p.getParent());
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

    private static void addTaskFromFileLine(String fileLine) {
        String[] stringParts = fileLine.split(" \\| ");
        boolean isDone = (stringParts[1].equals("X"));
        String taskType = stringParts[0];
        String description = stringParts[2];
        switch(taskType) {
        case "T":
            tasks.addTask(new Todo(description, isDone));
            break;
        case "D":
            tasks.addTask(new Deadline(description, isDone, stringParts[3]));
            break;
        case "E":
            tasks.addTask(new Event(description, isDone, stringParts[3], stringParts[4]));
            break;
        }
    }

    private static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task: tasks.getAllTasks()) {
            fw.write(getFileInputForTask(task) + System.lineSeparator());
        }
        fw.close();
    }

    private static String getFileInputForTask(Task task) {
        if (task instanceof Todo) {
            return "T | " + task.getStatusIcon() + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            return "D | " + task.getStatusIcon() + " | " + task.getDescription()
                    + " | " + ((Deadline)task).getBy();
        } else {
            return "E | " + task.getStatusIcon() + " | " + task.getDescription()
                    + " | " + ((Event)task).getFrom() + " | " + ((Event)task).getTo();
        }
    }

    private static void executeCommand(String userInputString)
            throws IllegalCommandException, EmptyDescriptionException, MissingPrefixException {
        String[] commandTypeAndArgs = splitCommandTypeAndArgs(userInputString);
        String commandType = commandTypeAndArgs[0];
        String commandArgs = commandTypeAndArgs[1];
        switch (commandType) {
        case "list":
            executeListTasks();
            break;
        case "mark":
            executeMarkTask(commandArgs);
            break;
        case "unmark":
            executeUnmarkTask(commandArgs);
            break;
        case "delete":
            executeDeleteTask(commandArgs);
            break;
        case "todo":
            executeAddTodo(commandArgs);
            break;
        case "deadline":
            executeAddDeadline(commandArgs);
            break;
        case "event":
            executeAddEvent(commandArgs);
            break;
        case "bye":
        case "exit":
            executeExitProgram();
        default:
            throw new IllegalCommandException();
        }
    }

    private static String[] splitCommandTypeAndArgs(String userInputString) {
        String[] stringParts = userInputString.split("\\s+", 2);
        return stringParts.length == 2 ? stringParts : new String[] {stringParts[0], ""};
    }

    private static String getTaskCountMessage() {
        return String.format("Now you have %1$d tasks in the list.", tasks.getSize());
    }

    private static void executeListTasks() {
        if (tasks.getSize() == 0) {
            ui.printMessages("No tasks in list. You're all caught up!");
            return;
        }
        System.out.println(DIVIDER);
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(INDENT_START + (i + 1) + "." + tasks.getTaskAtIndex(i));
        }
        System.out.println(DIVIDER);
    }

    private static void executeMarkTask(String commandArgs) {
        Task selectedTask = getSelectedTaskFromCommandArgs(commandArgs);
        selectedTask.markAsDone();
        ui.printMessages(MESSAGE_MARK_DONE, INDENT_EXTRA + selectedTask);
    }

    private static void executeUnmarkTask(String commandArgs) {
        Task selectedTask = getSelectedTaskFromCommandArgs(commandArgs);
        selectedTask.markAsNotDone();
        ui.printMessages(MESSAGE_MARK_UNDONE, INDENT_EXTRA + selectedTask);
    }

    private static void executeDeleteTask(String commandArgs) {
        Task selectedTask = getSelectedTaskFromCommandArgs(commandArgs);
        tasks.removeTask(selectedTask);
        ui.printMessages(MESSAGE_DELETED_TASK, INDENT_EXTRA + selectedTask, getTaskCountMessage());
    }

    private static Task getSelectedTaskFromCommandArgs(String commandArgs) {
        int taskNumber = Integer.parseInt(commandArgs);
        if (taskNumber < 1 || taskNumber > tasks.getSize()) {
            throw new IndexOutOfBoundsException();
        }
        return tasks.getTaskAtIndex(taskNumber - 1);
    }

    private static void executeAddTodo(String commandArgs) throws EmptyDescriptionException {
        String description = commandArgs.strip();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        addToTasksList(new Todo(description, false));
    }

    private static void executeAddDeadline(String commandArgs)
            throws EmptyDescriptionException, MissingPrefixException {
        int indexOfByPrefix = commandArgs.indexOf(DEADLINE_PREFIX_BY);
        if (indexOfByPrefix == -1) {
            throw new MissingPrefixException(DEADLINE_PREFIX_BY);
        }
        String description = commandArgs.substring(0, indexOfByPrefix).strip();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        String by = removePrefix(commandArgs.substring(indexOfByPrefix), DEADLINE_PREFIX_BY).strip();
        addToTasksList(new Deadline(description, false, by));
    }

    private static void executeAddEvent(String commandArgs)
            throws EmptyDescriptionException, MissingPrefixException {
        int indexOfFromPrefix = commandArgs.indexOf(EVENT_PREFIX_FROM);
        if (indexOfFromPrefix == -1) {
            throw new MissingPrefixException(EVENT_PREFIX_FROM);
        }
        int indexOfToPrefix = commandArgs.indexOf(EVENT_PREFIX_TO);
        if (indexOfToPrefix == -1) {
            throw new MissingPrefixException(EVENT_PREFIX_TO);
        }
        String description = commandArgs.substring(0, indexOfFromPrefix).strip();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        String from = removePrefix(commandArgs.substring(indexOfFromPrefix, indexOfToPrefix),
                EVENT_PREFIX_FROM).strip();
        String to = removePrefix(commandArgs.substring(indexOfToPrefix), EVENT_PREFIX_TO).strip();
        addToTasksList(new Event(description, false, from, to));
    }

    private static String removePrefix(String inputStr, String prefix) {
        return inputStr.replace(prefix, "");
    }

    private static void addToTasksList(Task newTask) {
        tasks.addTask(newTask);
        ui.printMessages(MESSAGE_ADDED_TASK, INDENT_EXTRA + newTask, getTaskCountMessage());
    }

    private static void executeExitProgram() {
        try {
            writeToFile(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.printExitMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        ui = new Ui();
        tasks = new TaskList();
        try {
            loadFileContents(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.printWelcomeMessage();
        while (true) {
            String userInputString = ui.getUserInput();
            try {
                executeCommand(userInputString);
            } catch (IllegalCommandException e) {
                ui.printMessages("Error: invalid command. Please try again!");
            } catch (NumberFormatException e) {
                ui.printMessages("Error: invalid index input. Please provide a number!");
            } catch (IndexOutOfBoundsException e) {
                ui.printMessages("Error: input out of bounds. List has " + tasks.getSize() + " tasks.");
            } catch (EmptyDescriptionException e) {
                ui.printMessages("Error: task description is empty. Please provide a description!");
            } catch (MissingPrefixException e) {
                ui.printMessages("Error: missing " + e.missingPrefix + " prefix.");
            }

            try {
                writeToFile(FILE_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
