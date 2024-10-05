package XiaoMe;

import java.util.Scanner;

/**
 * Represents the user interface for the application.
 * It handles user input and output.
 */
public class UI {
    Scanner in;

    /**
     * Constructs a UI object that initializes the Scanner for user input.
     */
    public UI () {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads a command input from the user.
     *
     * @return the command input as a String
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("""
                \tHello! I'm XiaoMe
                \tWhat can I do for you?""");
        showLine();
    }

    /**
     * Displays a horizontal line for better visual separation in the output.
     */
    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints the given command input to the user with surrounding lines for emphasis.
     *
     * @param commandInput the input to display to the user
     */
    public void printToUser(String commandInput) {
        showLine();
        System.out.println(commandInput);
        showLine();
    }
}
