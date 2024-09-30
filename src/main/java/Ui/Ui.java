package Ui;


import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import Commands.CommandResult;
import Tasks.Task;

import static Ui.Messages.LOGO;
import static Ui.Messages.CHAT_BAR;
import static Ui.Messages.CHAT_PREFIX;

public class Ui {
    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    /** Shows message(s) to the user */
    public void showToUser(String... message) {
        // for (String m : message) {
        //     out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        // }
        out.println(String.join("\n", message));
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        // showLine(true);
        String fullInputLine = in.nextLine();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }
        showLine(true);
        // showToUser("[Command entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    /**
     * Shows the result of a command execution to the user. Includes additional formatting to demarcate different
     * command execution segments.
     */
    public void showResultToUser(CommandResult result) {

        showToUser(result.getFeedback());
    }

    /**
     * Prints a welcome message from Cubone.
     * The welcome message includes the Cubone logo and a chat bar.
     */
    public void showWelcomeMsg(boolean LogFileRead, ArrayList<Task> inputed_tasks) {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm Cubone");
        if (LogFileRead) {
            System.out.println("I have read " + inputed_tasks.size() + " tasks from the Log file");
        }
        System.out.println("What can I do for you?");
        showLine();
    }


    public void showLine(boolean prefix) {
        if (prefix){
            System.out.println(CHAT_BAR + CHAT_PREFIX);
        }else{
            System.out.println(CHAT_BAR);
        }
    }

    public void showLine() {
        System.out.println(CHAT_BAR);
    }
}
