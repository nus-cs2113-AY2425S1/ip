package nova;

import nova.task.Task;

/**
 * Handles the user interface for displaying messages to the user.
 * This includes welcome messages, task status updates, and error notifications.
 */
public class Ui {

    /**
     * The separator line used to format messages.
     */
    public static final String SEPARATOR = "    ___________________________________________________________________";

    /**
     * A string representing a new line in the formatted messages.
     */
    public static final String NEW_LINE = "\n     ";

    /**
     * Displays a formatted message surrounded by separator lines.
     *
     * @param info The message to be displayed.
     */
    public static void displayMessage(String info) {
        System.out.println(SEPARATOR + NEW_LINE + info + "\n" + SEPARATOR);
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public static void displayWelcomeMessage() {
        displayMessage("Hello! I'm Nova" + NEW_LINE + "What can I do for you?");
    }

    /**
     * Displays a message when a task is marked as completed.
     *
     * @param task The task that has been marked as done.
     */
    public static void displayMarkMessage(Task task) {
        displayMessage("Nice! I've marked this task as done:" + NEW_LINE + "  " + task.getTaskInfo());
    }

    /**
     * Displays a message when a task is marked as not completed.
     *
     * @param task The task that has been marked as not done.
     */
    public static void displayUnmarkMessage(Task task) {
        displayMessage("OK, I've marked this task as not done yet:" + NEW_LINE + "  " + task.getTaskInfo());
    }

    /**
     * Displays a message indicating that an invalid input was entered.
     * It provides guidance on valid commands.
     */
    public static void displayInvalidInputMessage() {
        displayMessage("Invalid input. Please use one of the following commands:" + NEW_LINE +
                "1. list [yyyy-mm-dd]" + NEW_LINE +
                "2. mark <task number>" + NEW_LINE +
                "3. unmark <task number>" + NEW_LINE +
                "4. todo <task description>" + NEW_LINE +
                "5. deadline <task description> /by <due date>" + NEW_LINE +
                "6. event <task description> /from <start time> /to <end time>");
    }

    /**
     * Displays an invalid input message along with a custom message and task info.
     *
     * @param message The custom error message.
     * @param task    The task related to the error.
     */
    public static void displayInvalidInputMessage(String message, String task) {
        displayMessage(message + NEW_LINE + task);
    }

    /**
     * Displays a custom invalid input message.
     *
     * @param message The custom error message.
     */
    public static void displayInvalidInputMessage(String message) {
        displayMessage(message);
    }

    /**
     * Displays the separator line.
     */
    public static void displaySeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a message acknowledging that a task has been added.
     *
     * @param message       The message about the task that was added.
     * @param numberOfTasks The current number of tasks in the list.
     */
    public static void displayAcknowledgementMessage(String message, int numberOfTasks) {
        displayMessage("Got it. I've added this task:" + NEW_LINE + "  " + message +
                NEW_LINE + "Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Displays a message acknowledging that a task has been deleted.
     *
     * @param task The task that was deleted.
     */
    public static void displayDeleteMessage(Task task) {
        displayMessage("Noted. I've removed this task:" + NEW_LINE + "  " + task.getTaskInfo() +
                NEW_LINE + "Now you have " + (Task.getNumberOfTasks() - 1) + " tasks in the list.");
    }

    /**
     * Displays a generic error message related to storage operations.
     */
    public static void displayStorageError() {
        displayMessage("Storage error");
    }
}
