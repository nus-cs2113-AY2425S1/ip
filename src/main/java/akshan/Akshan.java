package akshan;

import akshan.command.CommandType;
import akshan.task.Task;
import akshan.task.TaskList;
import akshan.storage.StorageHandler;

import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Akshan {

    private static final String NAME = "Akshan";
    private static final String LOGO = """
 
             ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓███████▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓███████▓▒░ \s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓████████▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓████████▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\s
            """;
    private static final Map<CommandType, Function<String, String[]>> COMMAND_PARSERS = new HashMap<>();

    static {
        COMMAND_PARSERS.put(CommandType.TODO, Akshan::parseTodo);
        COMMAND_PARSERS.put(CommandType.DEADLINE, Akshan::parseDeadline);
        COMMAND_PARSERS.put(CommandType.EVENT, Akshan::parseEvent);
    }


    /**
     * Prints a horizontal line.
     */
    private static void printLine(){
        System.out.println("_______________________________________________________________");
    }

    /**
     * Main method to run the Akshan bot.
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        TaskList taskList = new TaskList();
        StorageHandler storageHandler = new StorageHandler(taskList);

        init();
        String line = input.nextLine();

        while (!line.equals(CommandType.BYE.getCommand())) {
            try {
                processCommand(line, taskList);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            printLine();
            storageHandler.saveData(taskList);
            line = input.nextLine();
        }
        bye();
    }

    /**
     * Initializes the bot, prints the logo and a welcome message.
     */
    private static void init(){
        System.out.println(LOGO);
        printLine();
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Exit bot, prints goodbye sequence.
     */
    private static void bye(){
        System.out.println( "Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Processes the user's command.
     *
     * @param command The user's input command.
     * @param taskList The list of tasks.
     * @throws IllegalArgumentException If the command is unknown or invalid.
     */
    private static void processCommand(String command, TaskList taskList) throws IllegalArgumentException {
        String[] splitInput = command.split("/");
        String commandTypeString = splitInput[0].split(" ")[0];
        CommandType commandType;
        try {
            commandType = CommandType.fromString(commandTypeString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown command: " + commandTypeString);
        }

        switch (commandType) {
        case LIST:
            taskList.printList();
            break;
        case MARK:
            processMarkUnmark(splitInput[0], taskList, true);
            break;
        case UNMARK:
            processMarkUnmark(splitInput[0], taskList, false);
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            processTask(commandType, command, taskList);
            break;
        case DELETE:
            processDelete(splitInput[0], taskList);
            break;
        default:
            throw new IllegalArgumentException("Uh oh, no command found in: " + command);
        }
    }

    /**
     * Processes a task command (TODO, DEADLINE, or EVENT).
     *
     * @param taskType The type of task.
     * @param command The full command string.
     * @param taskList The list of tasks.
     * @throws IllegalArgumentException If there's an error processing the task.
     */
    private static void processTask(CommandType taskType, String command, TaskList taskList) throws IllegalArgumentException {
        try {
            String[] params = COMMAND_PARSERS.get(taskType).apply(command);
            Task task = Task.createTask(taskType.getCommand(), params);
            addTaskToList(taskList, task);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error processing " + taskType + " task: " + e.getMessage());
        }
    }

    /**
     * Adds a task to the task list and prints a confirmation message.
     *
     * @param taskList The list of tasks.
     * @param task The task to be added.
     */
    private static void addTaskToList(TaskList taskList, Task task) {
        taskList.addItem(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Processes the mark or unmark command.
     *
     * @param command The command string.
     * @param taskList The list of tasks.
     * @param mark True if marking, false if unmarking.
     * @throws IllegalArgumentException If the command format is invalid or the task number is out of range.
     */
    private static void processMarkUnmark(String command, TaskList taskList, boolean mark) throws IllegalArgumentException {
        String[] parts = command.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid mark/unmark command format");
        }
        try {
            int index = Integer.parseInt(parts[1]);
            taskList.setItemStatus(index, mark);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task number: " + parts[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Task number out of range: " + parts[1]);
        }
    }

    /**
     * Processes the delete command.
     *
     * @param command The command string.
     * @param taskList The list of tasks.
     * @throws IllegalArgumentException If the command format is invalid or the task number is out of range.
     */
    private static void processDelete(String command, TaskList taskList) throws IllegalArgumentException {
        String[] parts = command.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid delete command format");
        }
        try {
            int index = Integer.parseInt(parts[1]);
            deleteTaskFromList(taskList, index - 1);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task number: " + parts[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Task number out of range: " + parts[1]);
        }
    }

    /**
     * Deletes a task from the task list and prints a confirmation message.
     *
     * @param taskList The list of tasks.
     * @param index The index of the task to be deleted.
     */
    private static void deleteTaskFromList(TaskList taskList, int index) {
        Task deletedTask = taskList.deleteItem(index);
        System.out.println("Got it. I've removed this task:");
        System.out.println("  " + deletedTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Parses a TODO command.
     *
     * @param command The full command string.
     * @return An array containing the task description.
     * @throws IllegalArgumentException If the todo task description is missing.
     */
    private static String[] parseTodo(String command) throws IllegalArgumentException {
        String[] parts = command.split(" ", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Todo task description is missing");
        }
        return new String[]{parts[1]};
    }

    /**
     * Parses a DEADLINE command.
     *
     * @param command The full command string.
     * @return An array containing the task description and deadline.
     * @throws IllegalArgumentException If the deadline task is missing description or deadline.
     */
    private static String[] parseDeadline(String command) throws IllegalArgumentException {
        String[] parts = command.split(" /by ", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Deadline task is missing description or deadline");
        }
        String[] descParts = parts[0].split(" ", 2);
        if (descParts.length < 2) {
            throw new IllegalArgumentException("Deadline task description is missing");
        }
        return new String[]{descParts[1], parts[1]};
    }

    /**
     * Parses an EVENT command.
     *
     * @param command The full command string.
     * @return An array containing the event description, start time, and end time.
     * @throws IllegalArgumentException If the event task is missing description, start time, or end time.
     */
    private static String[] parseEvent(String command) throws IllegalArgumentException {
        String[] parts = command.split(" /", 3);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Event task is missing description, start time, or end time");
        }
        String[] descParts = parts[0].split(" ", 2);
        if (descParts.length < 2) {
            throw new IllegalArgumentException("Event task description is missing");
        }
        String[] startParts = parts[1].split(" ", 2);
        String[] endParts = parts[2].split(" ", 2);
        if (startParts.length < 2 || endParts.length < 2) {
            throw new IllegalArgumentException("Event task is missing start time or end time");
        }
        return new String[]{descParts[1], startParts[1], endParts[1]};
    }
}