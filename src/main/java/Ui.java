import java.util.Scanner;

/**
 * Handles all interactions with the user, including displaying messages and reading commands.
 * This class provides methods to show welcome, goodbye, error messages, and to read user input.
 */
public class Ui {
    private Scanner scanner;

    // Constructs an Ui object, initializing the scanner to read user input.
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    // Displays the welcome message when the program starts.
    public void showWelcome() {
        printLine();
        System.out.println(" Hello! I'm KaiWen");
        System.out.println(" What can I do for you?");
        printLine();
    }

    // Displays the goodbye message when the user exits the program.
    public void showGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    // Displays an error message.
    public void showError(String message) {
        System.out.println(" OOPS!!! " + message);
    }

    // Prints a separator line to format the output.
    public void showLine() {
        printLine();
    }

    // Displays a loading error message if the task file cannot be loaded.
    public void showLoadingError() {
        showError("Error loading tasks from file.");
    }

    // Reads a command entered by the user.
    public String readCommand() {
        return scanner.nextLine();
    }

    // Prints a separator line for readability.
    private void printLine() {
        System.out.println("____________________________________________________________");
    }
}
