package medea.core;

import java.util.Scanner;
import java.io.PrintStream;

/**
 * Represents the user interface for the task management system.
 * This class handles user input and output, providing methods to read commands,
 * display messages, and show welcome or farewell messages.
 */
public class Ui {
    /** The length of the lines used for visual separation. */
    private final int LINE_LENGTH = 50;
    private Scanner in;
    private PrintStream out;

    /**
     * Constructs a Ui object, initializing the input and output streams.
     */
    public Ui() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }

    /**
     * Reads a command input from the user.
     *
     * @return the input command as a string
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Displays a line for visual separation in the output.
     */
    public void showLine() {
        out.println("=".repeat(LINE_LENGTH));
    }

    /**
     * Displays an error message to the user.
     *
     * @param e the exception to be displayed
     */
    public void showError(Exception e) {
        out.println("Error: " + e.getMessage());
    }

    /**
     * Displays a message to the user.
     *
     * @param msg the message to be displayed
     */
    public void showMsg(String msg) {
        out.println(msg);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        out.println("Hello! I'm...");
        out.println("""
                 __       __  ________  _______   ________   ______ \s
                /  \\     /  |/        |/       \\ /        | /      \\\s
                $$  \\   /$$ |$$$$$$$$/ $$$$$$$  |$$$$$$$$/ /$$$$$$  |
                $$$  \\ /$$$ |$$ |__    $$ |  $$ |$$ |__    $$ |__$$ |
                $$$$  /$$$$ |$$    |   $$ |  $$ |$$    |   $$    $$ |
                $$ $$ $$/$$ |$$$$$/    $$ |  $$ |$$$$$/    $$$$$$$$ |
                $$ |$$$/ $$ |$$ |_____ $$ |__$$ |$$ |_____ $$ |  $$ |
                $$ | $/  $$ |$$       |$$    $$/ $$       |$$ |  $$ |
                $$/      $$/ $$$$$$$$/ $$$$$$$/  $$$$$$$$/ $$/   $$/\s
                """);
        out.println("What can I do for you?");
    }

    /**
     * Displays a farewell message to the user.
     */
    public void showFarewell() {
        out.println("Bye. Hope to see you again soon!");
    }
}
