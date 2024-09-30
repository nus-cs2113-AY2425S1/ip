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

/**
 * The Ui class handles interactions with the user.
 * It reads user input, processes it, and displays messages to the user.
 * It also provides methods to show welcome messages and command results.
 * 
 * <p>Features include:
 * <ul>
 *   <li>Reading user input while ignoring comments and empty lines</li>
 *   <li>Displaying messages to the user</li>
 *   <li>Showing welcome messages and command results</li>
 * </ul>
 * 
 * <p>Usage example:
 * <pre>
 *     Ui ui = new Ui();
 *     String command = ui.getUserCommand();
 *     ui.showToUser("You entered: " + command);
 * </pre>
 * 
 * <p>Dependencies:
 * <ul>
 *   <li>java.util.Scanner</li>
 *   <li>java.io.InputStream</li>
 *   <li>java.io.PrintStream</li>
 * </ul>
 * 
 * <p>Constants:
 * <ul>
 *   <li>{@code COMMENT_LINE_FORMAT_REGEX}: Regular expression to identify comment lines</li>
 * </ul>
 * 
 * <p>Methods:
 * <ul>
 *   <li>{@code Ui()}: Default constructor using System.in and System.out</li>
 *   <li>{@code Ui(InputStream in, PrintStream out)}: Constructor with custom input and output streams</li>
 *   <li>{@code boolean shouldIgnore(String rawInputLine)}: Checks if a line should be ignored</li>
 *   <li>{@code boolean isCommentLine(String rawInputLine)}: Checks if a line is a comment</li>
 *   <li>{@code void showToUser(String... message)}: Displays messages to the user</li>
 *   <li>{@code String getUserCommand()}: Prompts and reads user command</li>
 *   <li>{@code void showResultToUser(CommandResult result)}: Displays command execution result</li>
 *   <li>{@code void showWelcomeMsg(boolean LogFileRead, ArrayList<Task> inputed_tasks)}: Displays welcome message</li>
 *   <li>{@code void showLine(boolean prefix)}: Displays a line with optional prefix</li>
 *   <li>{@code void showLine()}: Displays a line</li>
 * </ul>
 */
public class Ui {
    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a new Ui object with default input and output streams.
     * This constructor initializes the Ui with System.in as the input stream
     * and System.out as the output stream.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a new Ui object with the specified input and output streams.
     *
     * @param in  the input stream to read from
     * @param out the output stream to write to
     */
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


    /**
     * Displays a line with an optional prefix.
     *
     * @param prefix true if the line should have a prefix, false otherwise
     */
    public void showLine(boolean prefix) {
        if (prefix){
            System.out.println(CHAT_BAR + CHAT_PREFIX);
        }else{
            System.out.println(CHAT_BAR);
        }
    }

    /**
     * Displays a line with the default prefix.
     */
    public void showLine() {
        System.out.println(CHAT_BAR);
    }
}
