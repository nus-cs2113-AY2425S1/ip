package Ryan.utility;

import Ryan.tasks.Task;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles interactions with the user, displaying messages and taking input.
 */
public class Ui {
    private Scanner scanner;

    private static final String GREETING_MESSAGE_1 = "Hello! I'm Ryan.";
    private static final String GREETING_MESSAGE_2 = "What can I do for you?";
    private static final String GOODBYE_MESSAGE = "Goodbye! Have a nice day :D";
    private static final String ERROR_MESSAGE = "Error: ";
    private static final String ADD_MESSAGE = "Got it. I've added this task:";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    private static final String MARKED_MESSAGE = "Nice! I've marked this task as done:";
    private static final String UNMARKED_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String TASKS_FOUND_MESSAGE = "Here are the matching tasks in your list:";
    private static final String PRINT_LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String TOTAL_TASKS_MESSAGE_1 = "Now you have ";
    private static final String TOTAL_TASKS_MESSAGE_2 = " tasks in the list.";

    /**
     * Constructs a new Ui object for interacting with the user.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message when the program starts.
     */
    public static void printGreeting() {
        Ui.horizontalLine();
        System.out.println(GREETING_MESSAGE_1);
        System.out.println(GREETING_MESSAGE_2);
        Ui.horizontalLine();
    }

    /**
     * Prints the goodbye message when the program exits.
     */
    public static void printGoodbye() {
        Ui.horizontalLine();
        System.out.println(GOODBYE_MESSAGE);
        Ui.horizontalLine();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public static void showError(String message) {
        Ui.horizontalLine();
        System.out.println(ERROR_MESSAGE + message);
        Ui.horizontalLine();
    }

    /**
     * Displays a specified message.
     *
     * @param message The specified message to display.
     */
    public static void showMessage(String message) {
        Ui.horizontalLine();
        System.out.println(message);
        Ui.horizontalLine();
    }

    /**
     * Shows a message when a task is added.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks in the list.
     */
    public static void showTaskAdded(Task task, int totalTasks) {
        Ui.horizontalLine();
        System.out.println(ADD_MESSAGE);
        System.out.println("  " + task);
        System.out.println(TOTAL_TASKS_MESSAGE_1 + totalTasks + TOTAL_TASKS_MESSAGE_2);
        Ui.horizontalLine();
    }

    /**
     * Shows a message when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param totalTasks The total number of tasks in the list.
     */
    public static void showTaskDeleted(Task task, int totalTasks) {
        Ui.horizontalLine();
        System.out.println(DELETE_MESSAGE);
        System.out.println("  " + task);
        System.out.println(TOTAL_TASKS_MESSAGE_1 + totalTasks + TOTAL_TASKS_MESSAGE_2);
        Ui.horizontalLine();
    }

    /**
     * Shows a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public static void showTaskMarked(Task task) {
        Ui.horizontalLine();
        System.out.println(MARKED_MESSAGE);
        System.out.println("  " + task);
        Ui.horizontalLine();
    }

    /**
     * Shows a message when a task is marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public static void showTaskUnmarked(Task task) {
        Ui.horizontalLine();
        System.out.println(UNMARKED_MESSAGE);
        System.out.println("  " + task);
        Ui.horizontalLine();
    }

    /**
     * Displays all tasks that match the search query.
     *
     * @param tasks The list of tasks that match the search query.
     */
    public static void showFoundTasks(ArrayList<Task> tasks) {
        Ui.horizontalLine();
        System.out.println(TASKS_FOUND_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        Ui.horizontalLine();
    }

    /**
     * Displays all tasks in the list.
     *
     * @param tasks The list of tasks to display.
     */
    public static void printTasks(ArrayList<Task> tasks) {
        Ui.horizontalLine();
        System.out.println(PRINT_LIST_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        Ui.horizontalLine();
    }

    /**
     * Displays a horizontal line for formatting output.
     */
    public static void horizontalLine() {
        System.out.println("_".repeat(100));
    }
}
