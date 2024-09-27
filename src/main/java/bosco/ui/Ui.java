package bosco.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    public static final String DIVIDER =
            "\t________________________________________________________________________________";
    public static final String INDENT_START = "\t ";
    public static final String INDENT_EXTRA = "  ";
    public static final String MESSAGE_WELCOME = "Hello! I'm Bosco APD."
            + System.lineSeparator() + INDENT_START + "What can I do for you?";
    public static final String MESSAGE_EXIT = "Bye! Hope to see you again soon!";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_EMPTY_LIST = "No tasks in list. You're all caught up!";
    public static final String MESSAGE_MARK_DONE = "Nice! I've marked this task as done:";
    public static final String MESSAGE_MARK_UNDONE = "OK, I've marked this task as not done yet:";
    public static final String MESSAGE_ADDED_TASK = "Got it. I've added this task:";
    public static final String MESSAGE_DELETED_TASK = "Noted. I've removed this task:";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printMessages(String... messages) {
        System.out.println(DIVIDER);
        for (String message : messages) {
            System.out.println(INDENT_START + message);
        }
        System.out.println(DIVIDER);
    }

    public void printWelcomeMessage() {
        printMessages(MESSAGE_WELCOME);
    }

    public void printExitMessage() {
        printMessages(MESSAGE_EXIT);
    }

    public String getTaskCountMessage(int count) {
        return String.format("Now you have %1$d tasks in the list.", count);
    }

    public String getUserInput() {
        String userInputString = in.nextLine();
        // Silently consume blank lines
        while (userInputString.trim().isEmpty()) {
            userInputString = in.nextLine();
        }
        return userInputString;
    }
}
