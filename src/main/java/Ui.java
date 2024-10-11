import java.util.Scanner;

/**
 * Represents the user interface for the KBot application.
 * This class handles all interactions with the user, including
 * greeting the user, displaying messages, and printing task-related
 * information.
 */
public class Ui {
    private static final String SEPARATOR = "____________________________________________________________";

    /**
     * Greets the user with a welcome message and prompts for input.
     */
    public void greetUser() {
        printSeparator();
        System.out.println("Hello! I'm KBot");
        System.out.println("What can I do for you?");
        printSeparator();
    }

    /**
     * Prints a separator line to the console.
     */
    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The message to be displayed to the user.
     */
    public void showError(String errorMessage) {
        printSeparator();
        System.out.println(errorMessage);
        printSeparator();
    }

    /**
     * Displays a goodbye message when the user decides to exit the application.
     */
    public void exit() {
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The total number of tasks in the list after adding the task.
     */
    public void printTaskAddedMessage(Task task, int taskCount) {
        printSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printSeparator();
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printMarkDoneMessage(Task task) {
        printSeparator();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printSeparator();
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printMarkNotDoneMessage(Task task) {
        printSeparator();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        printSeparator();
    }

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task The task that was removed.
     * @param taskCount The total number of tasks in the list after deletion.
     */
    public void printDeleteTaskMessage(Task task, int taskCount) {
        printSeparator();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printSeparator();
    }

    /**
     * Gets user input from the console.
     *
     * @param scanner The scanner object used to read user input.
     * @return The input line entered by the user.
     */
    public String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }
}
