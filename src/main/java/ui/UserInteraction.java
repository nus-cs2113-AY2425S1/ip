package ui;

import task.TaskList;
import java.util.Scanner;

/**
 * Handles user interaction such as reading user input and displaying messages
 */
public class UserInteraction {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("           Welcome to Jer Chat Bot!                       ");
        System.out.println("           What can I do for you today?                   ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Display exit messages to the user.
     */
    public void showExitMessage() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" End of Program");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Reads command provided by user.
     * @return trimmed comamnd entered by user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints a divider line for message separation.
     */
    public void printDividerLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Display message to user.
     * @param message message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Display error message when loading task list from file.
     */
    public void showLoadingError() {
        System.out.println("Error loading the task list from file.");
    }

    /**
     * Display the task list to user.
     * @param tasks task list to display
     */
    public void showTaskList(TaskList tasks) {
        printDividerLine();
        System.out.println(" Current Task List:");
        if (tasks.isEmpty()) {
            System.out.println(" No tasks in your list currently.");
        } else {
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.printf("%d. %s%n", i + 1, tasks.getTask(i));
            }
        }
        printDividerLine();
    }
}
