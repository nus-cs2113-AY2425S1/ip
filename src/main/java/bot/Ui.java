package bot;

import java.util.Scanner;

/**
 * Handles user interaction, such as displaying messages and reading commands.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm TobyBot");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's command as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a separator line in the console.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays an error message for loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks.");
    }

    /**
     * Displays a general error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a general message.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}