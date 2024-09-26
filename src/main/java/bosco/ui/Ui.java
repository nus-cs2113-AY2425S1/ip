package bosco.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER =
            "\t________________________________________________________________________________";
    private static final String INDENT_START = "\t ";
    private static final String INDENT_EXTRA = "  ";
    private static final String MESSAGE_WELCOME = "Hello! I'm Bosco APD."
            + System.lineSeparator() + INDENT_START + "What can I do for you?";
    private static final String MESSAGE_EXIT = "Bye! Hope to see you again soon!";

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

    public String getUserInput() {
        String userInputString = in.nextLine();
        // Silently consume blank lines
        while (userInputString.trim().isEmpty()) {
            userInputString = in.nextLine();
        }
        return userInputString;
    }
}
