package lovespiritual;

import lovespiritual.task.Task;

import java.util.ArrayList;

/**
 * Handles displaying messages to the user, including the welcome message, task lists, and error messages.
 */
public class UI {
    private static final String SEPARATOR = "_".repeat(30);

    /**
     * Prints the exit message when the user leaves the application.
     */
    public static void printExitMessage() {
        System.out.println(SEPARATOR);
        System.out.println("Goodbye! (｡•‿•｡) Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the welcome message when the application starts.
     */
    public static void printWelcomeMessage() {
        System.out.println(SEPARATOR);
        System.out.println("Hiya! (✿◠‿◠) I'm lovespiritual, ready to help!");
        System.out.println("How can I assist? (•‿•) I'm all ears!");
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the list of tasks currently stored in the local disk.
     *
     * @param tasks List of tasks to display.
     */
    public static void printList(ArrayList <Task> tasks) {
        System.out.println(SEPARATOR);
        if (tasks.isEmpty()) {
            System.out.println("There is nothing on your list yet!");
        } else {
            System.out.println("Here's your list! (・∀・) Ready to tackle it?");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Prints an error message when a custom exception occurs.
     *
     * @param message Error message to display.
     */
    public static void printError(String message) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    /**
     * Prints a generic message when an unexpected exception occurs.
     */
    public void printUnexpectedError() {
        System.out.println(SEPARATOR);
        System.out.println("Oh no! (＞﹏＜) Something went a little wrong...");
        System.out.println(SEPARATOR);
    }
}
