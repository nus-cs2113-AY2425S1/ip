package poppy;

import java.util.Scanner;

/**
 * The {@code Ui} class handles user interactions in the application.
 * It provides methods to get user input and display messages to the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new {@code Ui} object and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Retrieves the user input from the console.
     *
     * @return A string containing the user's input.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Poppy");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a specified message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Closes the scanner to free up resources.
     */
    public void close() {
        scanner.close();
    }
}
