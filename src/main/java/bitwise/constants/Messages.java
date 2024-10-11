package bitwise.constants;

/**
 * The {@code Messages} class contains constants representing various messages displayed to the user in the application.
 * These messages are used for user feedback and communication throughout the task management process.
 */
public class Messages {
    public static final String MESSAGE_WELCOME = Constants.SECTION_BREAK + Constants.INDENTATION + "Hello from\n" +
            Constants.LOGO + "\n" + Constants.INDENTATION + "How may I help you today?\n" + Constants.LINE_BREAK;
    public static final String MESSAGE_EXIT = Constants.LINE_BREAK + Constants.INDENTATION + "Bye, see you soon!\n" + Constants.SECTION_BREAK;
    public static final String MESSAGE_MARKED = "Awesome, I've marked this task as completed!";
    public static final String MESSAGE_UNMARKED = "I've added the task back in";
    public static final String MESSAGE_TASK_LIST = "Here are the tasks in your list: ";
    public static final String MESSAGE_ADDED = "Got it! Added:\n";
    public static final String MESSAGE_DELETED = "Got it! Deleted:\n";
    public static final String MESSAGE_FOUND = "Here's what I found!";
    public static final String MESSAGE_NOT_FOUND = "No matching results :(";

}
