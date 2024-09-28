package bosco.ui;

import java.util.Scanner;

/**
 * Represents a UI object that manages command line interactions with the user.
 */
public class Ui {
    public static final String DIVIDER =
            "\t________________________________________________________________________________";
    public static final String INDENT_START = "\t ";
    public static final String INDENT_EXTRA = "  ";
    public static final String MESSAGE_WELCOME = "Hello! I'm Bosco APD."
            + System.lineSeparator() + INDENT_START + "What can I do for you?";
    public static final String MESSAGE_EXIT = "Bye! Hope to see you again soon!";
    public static final String MESSAGE_MARK_DONE = "Nice! I've marked this task as done:";
    public static final String MESSAGE_MARK_UNDONE = "OK, I've marked this task as not done yet:";
    public static final String MESSAGE_ADDED_TASK = "Got it. I've added this task:";
    public static final String MESSAGE_DELETED_TASK = "Noted. I've removed this task:";

    private final Scanner in;

    /**
     * Class constructor.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints the input messages between dividers
     * and with the required formatting.
     *
     * @param messages Variable number of messages to be printed.
     */
    public void printMessages(String... messages) {
        System.out.println(DIVIDER);
        for (String message : messages) {
            System.out.println(INDENT_START + message);
        }
        System.out.println(DIVIDER);
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        printMessages(MESSAGE_WELCOME);
    }

    /**
     * Prints the exit message.
     */
    public void printExitMessage() {
        printMessages(MESSAGE_EXIT);
    }

    /**
     * Returns a formatted string containing the input count.
     *
     * @param count Integer count of tasks.
     * @return Formatted string containing the count.
     */
    public String getTaskCountMessage(int count) {
        return String.format("Now you have %1$d tasks in the list.", count);
    }

    /**
     * Gets the user input from the command line.
     *
     * @return String of the raw user input.
     */
    public String getUserInput() {
        String userInputString = in.nextLine();
        // Silently consume blank lines
        while (userInputString.trim().isEmpty()) {
            userInputString = in.nextLine();
        }
        return userInputString;
    }
}
