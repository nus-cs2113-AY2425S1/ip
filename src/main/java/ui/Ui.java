package ui;

import task.Task;
import task.TaskList;

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
    private static final String WELCOME_MESSAGE = "Hello! I'm Iris!";
    private static final String END_CHAT_MESSAGE = "Bye! Hope to see you again soon!";
    private static final String EMPTY_LIST_MESSAGE = "No tasks added, add more now!";
    private static final String NONEMPTY_LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String MARK_AS_COMPLETED_MESSAGE = "Nice! I've marked this task as done:";
    private static final String UNMARK_FROM_COMPLETED_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String SAVE_SUCCESS_MESSAGE = "Saved task list successfully!";
    private static final String SAVE_ERROR_MESSAGE = "Failed to save task list.";
    private static final String LOAD_SUCCESS_MESSAGE = "Loaded task list successfully!";
    private static final String LOAD_ERROR_MESSAGE = "Failed to load task list, initializing with an empty list.";

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

    /**
     * Prints a message confirming that a task has been added to the task list,
     * displaying the newly added task and the current number of tasks in the list.
     *
     * @param task The new task has been added.
     * @param size The size of the task list
     */
    public static void printAddTaskMessage(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have "
                + size
                + " tasks in the list");
    }

    public static void printEmptyListMessage() {
        System.out.println(EMPTY_LIST_MESSAGE);
    }

    public static void printNonEmptyListMessage() {
        System.out.println(NONEMPTY_LIST_MESSAGE);
    }

    public static void printMarkMessage() {
        System.out.println(MARK_AS_COMPLETED_MESSAGE);
    }

    public static void printUnmarkMessage() {
        System.out.println(UNMARK_FROM_COMPLETED_MESSAGE);
    }

    public static void printTask(Task task) {
        System.out.println(task);
    }

    public static void printSaveSuccessMessage() {
        System.out.println(SAVE_SUCCESS_MESSAGE);
    }

    public static void printSaveErrorMessage(String errorMessage) {
        System.out.println(SAVE_ERROR_MESSAGE);
        Ui.showErrorMessage(errorMessage);
    }

    public static void printLoadSuccessMessage() {
        System.out.println(LOAD_SUCCESS_MESSAGE);
        Ui.showDivider();
    }

    public static void printLoadErrorMessage() {
        System.out.println(LOAD_ERROR_MESSAGE);
        Ui.showDivider();
    }
}
