package ui;

import java.util.Scanner;

/**
 * The Ui class is responsible for managing all interactions with the user,
 * including displaying messages and reading input commands.
 * It uses the console for input/output operations.
 *
 * This class provides utility methods to display error messages,
 * dividers, and specific chat messages like welcome and exit messages.
 * It also reads user commands from the console.
 *
 * @author Tan Ping Hui
 */
public class Ui {
    private static final String DIVIDER = "---------------------------------------------";
    private static final String WELCOME_MESSAGE = "Hello! I'm Iris!\nTell me your needs!";
    private static final String END_CHAT_MESSAGE = "Bye! Hope to see you again soon!";

    private final Scanner scanner;

    /**
     * Constructs a Ui object and initializes the Scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input from the console.
     *
     * @return The user's input command as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be shown.
     */
    public static void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a divider line for better visual separation of text in the console.
     */
    public static void showDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public static void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(DIVIDER);
    }

    /**
     * Displays the end chat message when the program exits.
     */
    public static void showEndChatMessage() {
        System.out.println(END_CHAT_MESSAGE);
    }
}
