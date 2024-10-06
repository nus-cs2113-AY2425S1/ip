package aether.ui;

import java.util.Scanner;

/**
 * Handles user interactions by managing input and delegating output to the {@code Display} class.
 * <p>
 * The {@code Ui} class serves as the interface between the user and the application,
 * facilitating the display of messages and the retrieval of user input. It centralizes
 * all user interface operations, ensuring consistent communication and interaction flow.
 * </p>
 */
public class Ui {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Displays the start screen with the application's logo and welcome message.
     * <p>
     * This method delegates the task of showing the start screen to the {@code Display} class,
     * ensuring that the welcome interface is presented to the user when the application launches.
     * </p>
     */
    public static void showStartScreen() {
        Display.showStartScreen();
    }

    /**
     * Displays the end screen with a goodbye message.
     * <p>
     * This method delegates the task of showing the end screen to the {@code Display} class,
     * ensuring that a farewell message is presented to the user when the application is about to exit.
     * </p>
     */
    public static void showEndScreen() {
        Display.showEndScreen();
    }

    /**
     * Displays a response message from Aether.
     * <p>
     * This method delegates the task of displaying a response message to the {@code Display} class,
     * prefixing the message with "Aether:" to indicate that it originates from the assistant.
     * </p>
     *
     * @param message The message to be displayed to the user.
     */
    public static void response(String message) {
        Display.response(message);
    }

    /**
     * Displays a response message without the "Aether:" prefix.
     * <p>
     * This method delegates the task of displaying a message to the {@code Display} class
     * without adding any prefix. It is useful for displaying lists or detailed information
     * where the prefix might be redundant.
     * </p>
     *
     * @param message The message to be displayed to the user.
     */
    public static void newLineResponse(String message) {
        Display.newLineResponse(message);
    }

    /**
     * Prints a separator line to the console.
     * <p>
     * This method delegates the task of printing a separator to the {@code Display} class,
     * providing a visual divider between different sections of the user interface.
     * </p>
     */
    public static void printSeparator() {
        Display.printSeparator();
    }

    /**
     * Reads input from the user.
     * <p>
     * This method captures the user's input from the console, allowing the application
     * to process commands and respond accordingly. It utilizes a {@code Scanner} to read
     * the input line entered by the user.
     * </p>
     *
     * @return The user's input as a {@code String}.
     */
    public static String getInput() {
        return scanner.nextLine();
    }
}
