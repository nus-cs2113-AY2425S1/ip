package main;

import task.Task;

import java.util.ArrayList;

/**
 * The Ui class handles all the user interface interactions by displaying messages to the user.
 * It provides methods to print responses, error messages, and updates about the tasks list.
 */
public class Ui {

    public static final String DRAW_HORIZONTAL_LINE = "\t________________________________________";
    public static final String INVALID_EVENT_INPUT_MESSAGE = "event <event name> /from <start date/time> /end <end date/time>";
    public static final String INVALID_DEADLINE_INPUT_MESSAGE = "deadline <deadline name> /by <deadline>";
    public static final String INVALID_TODO_INPUT_MESSAGE = "todo <task name>";
    private static final String INVALID_MARK_MESSAGE = "mark <task index>";
    private static final String INVALID_UNMARK_MESSAGE = "unmark <task index>";
    private static final String INVALID_DELETE_MESSAGE = "delete <task index>";

    /**
     * Prints a horizontal line for separating sections in the user interface.
     */
    public void printHorizontalLine() {
        System.out.println(DRAW_HORIZONTAL_LINE);
    }

    /**
     * Prints the introduction message when the program starts, including the bot's name and greeting.
     */
    public void printIntroMessage() {
        printHorizontalLine();
        System.out.println("\tHello! I'm");
        System.out.println("\t.___  ___.  _______  __      \n" +
                "\t|   \\/   | |   ____||  |     \n" +
                "\t|  \\  /  | |  |__   |  |     \n" +
                "\t|  |\\/|  | |   __|  |  |     \n" +
                "\t|  |  |  | |  |____ |  `----.\n" +
                "\t|__|  |__| |_______||_______|\n" +
                "\t                             ");
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints the goodbye message when the user exits the program.
     */
    public void printByeMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Prints an error message indicating an invalid task format input from the user,
     * along with the correct formats for each type of task.
     */
    public void printInvalidTaskMessage() {
        System.out.println("\tInvalid command format:" + System.lineSeparator() + "\t\t" + INVALID_TODO_INPUT_MESSAGE
                + System.lineSeparator() + "\t\t" + INVALID_DEADLINE_INPUT_MESSAGE + System.lineSeparator() + "\t\t"
                + INVALID_EVENT_INPUT_MESSAGE + System.lineSeparator() + "\t\t"
                + INVALID_MARK_MESSAGE + System.lineSeparator() + "\t\t"
                + INVALID_UNMARK_MESSAGE + System.lineSeparator() + "\t\t"
                + INVALID_DELETE_MESSAGE);
    }

    /**
     * Prints an error message when a task description is empty.
     */
    public void printTaskDescriptionEmptyMessage() {
        System.out.println("\tError: The task description cannot be empty.");
    }
    
    public void printFindDescriptionEmptyMessage() {
        System.out.println("\tError: The find description cannot be empty.");
    }

    /**
     * Prints a message confirming that a task has been added to the list.
     * @param itemArrayList The current list of tasks.
     * @param task The task that was added.
     */
    public void printAddedMessage(ArrayList<Task> itemArrayList, Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + (itemArrayList.size()) + " tasks in the list.");
    }

    /**
     * Prints a generic error message for unknown issues.
     */
    public void printUnknownErrorMessage() {
        System.out.println("Unknown error experienced.");
    }

    /**
     * Prints a message confirming that a task has been marked as done.
     * @param itemArrayList The current list of tasks.
     * @param itemNum The task index that was marked as done.
     */
    public void printTaskMarkedMessage(ArrayList<Task> itemArrayList, int itemNum) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + itemArrayList.get(itemNum - 1));
    }

    /**
     * Prints a message confirming that a task has been unmarked (marked as not done).
     * @param itemArrayList The current list of tasks.
     * @param itemNum The task index that was unmarked.
     */
    public void printTaskUnmarkedMessage(ArrayList<Task> itemArrayList, int itemNum) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + itemArrayList.get(itemNum - 1));
    }

    /**
     * Prints an error message when the user provides an index that is out of range
     * for marking, unmarking, or deleting tasks.
     */
    public void printInputIndexOutOfRangeMessage() {
        System.out.println("\tInput index number out of range.");
    }

    /**
     * Prints an error message when the user provides a non-integer index for task operations.
     */
    public void printInputIndexNotAnIntegerMessage() {
        System.out.println("\tInput index was not a integer.");
    }

    /**
     * Prints a message confirming that a task has been deleted from the list.
     * @param itemArrayList The current list of tasks.
     * @param task The task that was deleted.
     */
    public void printTaskDeletedMessage(ArrayList<Task> itemArrayList, Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + itemArrayList.size() + " tasks in the list.");
    }
}

