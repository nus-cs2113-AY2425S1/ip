package grok.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello, I am Grok! Your favourite personal assistant that helps you keep track of tasks :)");
        System.out.println("Here are the list of things Grok can do for you:");
        System.out.println("1. Create a todo task eg. [todo read book]");
        System.out.println("2. Create an event task eg. [event read book /from 2pm /to 4pm]");
        System.out.println("3. Create a deadline task eg. [deadline read book /by 2pm]");
        System.out.println("4. Type either mark or unmark and the task number to indicate completion of task");
        System.out.println("5. Type delete followed by the task number to remove a task from your list");
        System.out.println("6. Type list to view your list of tasks.");
        System.out.println("7. Type bye to exit the programme");
        showLine();
    }

    /**
     * Reads a command from the user input.
     * @return The command input by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a divider line to the user.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.err.println(message);
    }

    /**
     * Displays a loading error message when there is an issue loading tasks.
     */
    public void showLoadingError() {
        System.err.println("Error loading tasks.");
    }

    /**
     * Displays a custom message to the user.
     * @param message The message to display.
     */
    public void showMsg(String message) {
        System.out.println(message);
    }
}
