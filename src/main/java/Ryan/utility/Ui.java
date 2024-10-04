package Ryan.utility;

import Ryan.tasks.Task;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles interactions with the user, displaying messages and taking input.
 */
public class Ui {
    private Scanner scanner;

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
        System.out.println("Hello! I'm Ryan.");
        System.out.println("What can I do for you?");
        Ui.horizontalLine();
    }

    /**
     * Prints the goodbye message when the program exits.
     */
    public static void printGoodbye() {
        Ui.horizontalLine();
        System.out.println("Goodbye! Have a great day.");
        Ui.horizontalLine();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public static void showError(String message) {
        Ui.horizontalLine();
        System.out.println("Error: " + message);
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
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
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
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        Ui.horizontalLine();
    }

    /**
     * Shows a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public static void showTaskMarked(Task task) {
        Ui.horizontalLine();
        System.out.println("Nice! I've marked this task as done:");
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
        System.out.println("OK, I've marked this task as not done yet:");
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
        System.out.println("Here are the matching tasks in your list:");
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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        Ui.horizontalLine();
    }

    /**
     * Displays a horizontal line for formatting output.
     */
    public static void horizontalLine() {
        System.out.println("_________________________________________________________");
    }

}
