package wildpeace.task;

import java.util.Scanner;

/**
 * Handles user interactions by reading input from the user and displaying messages.
 * This class provides methods for reading commands, showing messages, showing error messages,
 * and providing instructions to the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes a Scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the user input.
     *
     * @return The command input by the user as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showLine(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.err.println(message);
    }

    /**
     * Displays the user guide with instructions on how to input tasks and mark/unmark them.
     */
    public void showGuide() {
        System.out.println("List your plans in the following format:");
        System.out.println("todo/ deadline/ event + task, e.g., todo homework");
        System.out.println("You may also mark/unmark tasks by: mark/unmark + task");
        System.out.println("Return to tutorial by entering Q");
    }

    /**
     * Closes the scanner used to read user input.
     */
    public void close() {
        scanner.close();
    }
}
