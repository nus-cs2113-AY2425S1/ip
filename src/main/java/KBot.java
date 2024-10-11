import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the main bot application that interacts with the user and manages tasks.
 * This class handles user commands, task management, and interactions with other components
 * such as the user interface, storage, and parser.
 */
public class KBot {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private Scanner scanner;

    /**
     * Initializes a new KBot instance with a TaskList, Ui, Storage, Parser, and Scanner.
     */
    public KBot() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        scanner = new Scanner(System.in);
    }

    /**
     * The entry point of the KBot application.
     * Creates an instance of KBot and starts the bot.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        KBot bot = new KBot();
        bot.run();
    }

    /**
     * Runs the main loop of the KBot, greeting the user and processing commands until
     * the user decides to exit.
     */
    public void run() {
        ui.greetUser();

        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.getUserInput(scanner);
                isRunning = handleCommand(userInput);
            } catch (KBotException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.exit();
    }

    /**
     * Handles user commands by parsing the input and executing the corresponding task.
     *
     * @param input The user command input.
     * @return A boolean indicating whether the bot should continue running.
     * @throws KBotException If the command is unknown or invalid.
     */
    private boolean handleCommand(String input) throws KBotException {
        String[] inputParts = parser.parseInput(input);
        String command = inputParts[0];
        String argument = (inputParts.length > 1) ? inputParts[1] : "";

        switch (command) {
            case "bye":
                return false;
            case "list":
                listTasks();
                break;
            case "mark":
                int markIndex = parser.parseTaskNumber(argument);
                markTaskAsDone(markIndex);
                break;
            case "unmark":
                int unmarkIndex = parser.parseTaskNumber(argument);
                markTaskAsNotDone(unmarkIndex);
                break;
            case "todo":
                if (argument.isEmpty()) {
                    throw new KBotException("The description of a todo cannot be empty.");
                } else {
                    addTodoTask(argument);
                }
                break;
            case "deadline":
                parser.validateDeadlineArgument(argument);
                addDeadlineTask(argument);
                break;
            case "event":
                parser.validateEventArgument(argument);
                addEventTask(argument);
                break;
            case "delete":
                int deleteIndex = parser.parseTaskNumber(argument);
                deleteTask(deleteIndex);
                break;
            case "find":
                handleFindCommand(input);
                break;
            default:
                throw KBotException.unknownCommand();
        }

        storage.saveTasksToFile(taskList.getTasks()); // Save the updated task list after each change
        return true;
    }

    /**
     * Handles the "find" command to search for tasks matching the given keyword.
     * Displays matching tasks to the user.
     *
     * @param input The full user command input including the keyword to search for.
     */
    private void handleFindCommand(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Please provide a keyword to search for.");
            return;
        }

        String keyword = parts[1];
        ArrayList<Task> matchingTasks = taskList.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found for keyword: " + keyword);
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : matchingTasks) {
                int originalIndex = taskList.getTasks().indexOf(task) + 1; // Get the original index (1-based)
                System.out.println(originalIndex + "." + task.toString());
            }
        }
    }


    /**
     * Lists all tasks currently in the task list and displays them to the user.
     */
    private void listTasks() {
        ui.printSeparator();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.getTask(i));
        }
        ui.printSeparator();
    }

    /**
     * Adds a new todo task to the task list and notifies the user.
     *
     * @param description The description of the todo task.
     */
    private void addTodoTask(String description) {
        Task task = new Todo(description);
        taskList.addTask(task);
        ui.printTaskAddedMessage(task, taskList.size());
    }

    /**
     * Adds a new deadline task to the task list and notifies the user.
     *
     * @param input The input string containing the description and deadline date.
     * @throws KBotException If the deadline format is invalid.
     */
    private void addDeadlineTask(String input) throws KBotException {
        String[] parts = input.split(" /by ");
        if (parts.length < 2) {
            throw new KBotException("The deadline description or date is missing.");
        }
        String description = parts[0];
        String time = parts[1]; // This will be the deadline date
        Task task = new Deadline(description, time);
        taskList.addTask(task);
        ui.printTaskAddedMessage(task, taskList.size());
    }

    /**
     * Adds a new event task to the task list and notifies the user.
     *
     * @param input The input string containing the description, start time, and end time.
     * @throws KBotException If the event format is invalid.
     */
    private void addEventTask(String input) throws KBotException {
        String[] parts = input.split(" /from | /to ");
        if (parts.length < 3) {
            throw new KBotException("The event description or timing is missing.");
        }
        String description = parts[0];
        String from = parts[1]; // Start time
        String to = parts[2]; // End time
        Task task = new Event(description, from, to);
        taskList.addTask(task);
        ui.printTaskAddedMessage(task, taskList.size());
    }

    /**
     * Marks a specified task as done and notifies the user.
     *
     * @param index The index of the task to mark as done.
     * @throws KBotException If the task index is invalid.
     */
    private void markTaskAsDone(int index) throws KBotException {
        validateTaskIndex(index);
        taskList.getTask(index).markAsDone();
        ui.printMarkDoneMessage(taskList.getTask(index));
    }

    /**
     * Marks a specified task as not done and notifies the user.
     *
     * @param index The index of the task to mark as not done.
     * @throws KBotException If the task index is invalid.
     */
    private void markTaskAsNotDone(int index) throws KBotException {
        validateTaskIndex(index);
        taskList.getTask(index).markAsNotDone();
        ui.printMarkNotDoneMessage(taskList.getTask(index));
    }

    /**
     * Validates that the task index is within the bounds of the task list.
     *
     * @param index The index of the task to validate.
     * @throws KBotException If the task index is invalid.
     */
    private void validateTaskIndex(int index) throws KBotException {
        if (index >= taskList.size() || index < 0) {
            throw new KBotException("Invalid task number.");
        }
    }

    /**
     * Deletes a specified task from the task list and notifies the user.
     *
     * @param taskIndex The index of the task to delete.
     * @throws KBotException If the task index is invalid.
     */
    public void deleteTask(int taskIndex) throws KBotException {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new KBotException("OOPS!!! Task does not exist.");
        }
        Task removedTask = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);
        ui.printDeleteTaskMessage(removedTask, taskList.size());
    }
}
