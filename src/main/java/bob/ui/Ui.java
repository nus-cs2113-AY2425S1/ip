package bob.ui;
import bob.task.Task;
import bob.task.TaskList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface of the chatbot.
 * It handles the interactions with the user, including displaying messages
 * and reading user input.
 */
public class Ui {
    public static final String SEPARATOR = "____________________________________________________________";
    Scanner scanner;

    /**
     * Initializes the Ui object and creates a new Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void showWelcome() {
        String logo = """
              ____        _
             | |_) \\ ___ | |___
             |  _ //  _  \\   _ \\
             | |_)\\\\ (_) /  |_) |
             |____/ \\___/|_|___/
            """;

        System.out.println(logo);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads a line of user input from the console.
     *
     * @return The command input by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a separator line in the console.
     */
    public void showSeparatorLine() {
        System.out.println(SEPARATOR);
    }

    /**
     * Prints a message indicating that the task list is empty.
     */
    public void printEmptyListMessage() {
        System.out.println("Sorry! Your list is empty.");
    }

    /**
     * Prints a message indicating that the task is already marked as done.
     */
    public void printTaskAlreadyDoneMessage() {
        System.out.println("Sorry! This task is already marked as done.");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Prints a message indicating an invalid task number.
     *
     * @param tasks The current list of tasks to display the valid range.
     */
    public void printInvalidTaskNumberMessage(TaskList tasks) {
        System.out.println("Please input a valid task number from 1 to " + tasks.getSize() + ".");
    }

    /**
     * Prints a message indicating that the input task number is not a valid integer.
     */
    public void printInvalidInputTypeMessage() {
        System.out.println("The input task number must be an integer.");
    }

    /**
     * Prints a message indicating that the task is already unmarked.
     */
    public void printTaskAlreadyUnmarkedMessage() {
        System.out.println("Sorry! This task is already unmarked.");
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that has been unmarked.
     */
    public void showUnmarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Prints a message indicating that the task description cannot be empty.
     *
     * @param taskType The type of task (examples, "todo", "deadline", "event").
     */
    public void printEmptyDescription(String taskType) {
        System.out.println("Sorry! The description of " + taskType + " cannot be empty.");
    }

    /**
     * Prints a message indicating that a task has been added to the list.
     *
     * @param tasks The current list of tasks to display the added task and size.
     */
    public void printAddedTask (TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.getTask(tasks.getSize() - 1));
        System.out.println("Now you have " + tasks.getSize() + " "
                + (tasks.getSize() == 1 ? "task" : "tasks") + " in the list.");
    }

    /**
     * Prints the current list of tasks.
     *
     * @param tasks The list of tasks to be printed.
     */
    public void printTaskList(TaskList tasks) {
        System.out.println("Here " + (tasks.getSize() == 1 ? "is" : "are") + " the "
                + (tasks.getSize() == 1 ? "task" : "tasks") + " in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.getTaskList().get(i));
        }
    }

    /**
     * Prints a message indicating that the provided deadline format is invalid.
     */
    public void printInvalidDeadline() {
        System.out.println("Sorry! Please provide a valid deadline with '/by <date/time>'.");
    }

    /**
     * Prints a message indicating that the provided event format is invalid.
     */
    public void printInvalidEvent() {
        System.out.println("Sorry! Please provide a valid event with '/from <start date/time> /to <end date/time>'.");
    }

    /**
     * Displays a message when a task is deleted from the list.
     *
     * @param removedTask The task that has been removed.
     * @param size The new size of the task list.
     */
    public void showDeleteTaskMessage(Task removedTask, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + size + (size == 1 ? " task" : " tasks") + " in the list.");
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an error message when a line cannot be parsed.
     *
     * @param lineNumber The line number where the error occurred.
     * @param message The error message to be displayed.
     */
    public void printErrorParsingLineMessage(int lineNumber, String message) {
        System.out.println("Error parsing line " + lineNumber + ": " + message);
    }

    /**
     * Prints a message when the specified file cannot be found.
     *
     * @param message The error message to be displayed.
     */
    public void printFileNotFound(String message) {
        System.out.println("File not found: " + message);
    }

    /**
     * Prints a message indicating the result of creating a new directory or file.
     *
     * @param itemType The type of item (folder or file) being created.
     * @param success Whether the creation was successful.
     */
    public void printCreationMessage(String itemType, boolean success) {
        if (!success) {
            System.out.println(itemType + " failed to be created.");
            return;
        }
        System.out.println("Creating a new " + itemType + " because " + itemType + " has not existed yet.");
    }

    /**
     * Prints an error message when saving tasks fails.
     *
     * @param message The error message to be displayed.
     */
    public void printErrorSavingTasks(String message) {
        System.out.println("An error occurred while saving the tasks: " + message);
    }

    /**
     * Prints an error message when appending a task fails.
     *
     * @param message The error message to be displayed.
     */
    public void printErrorAppendingTask(String message) {
        System.out.println("An error occurred while appending the task: " + message);
    }

    /**
     * Prints a message indicating that an invalid command was entered.
     */
    public void printInvalidCommand() {
        System.out.println("Sorry! This is an invalid command. I don't understand what you mean.");
    }

    public void showMatchingTasks(List<Task> taskList, List<Integer> matchingIndices) {
        System.out.println("Here are the matching tasks in your list:");
        for (Integer index : matchingIndices) {
            System.out.println((index + 1) + "." + taskList.get(index));
        }
    }

    public void printNoMatchingTasks() {
        System.out.println("Sorry! There are no matching tasks in your list.");
    }
}