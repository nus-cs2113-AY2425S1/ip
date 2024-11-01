package constants;

/**
 * This class defines a set of constants used for handling commands
 * in Bento. Each constant represents a command string
 * or an index used in command processing.
 */
public class Command {

    /** Command to exit the program. */
    public static final String BYE_COMMAND = "bye";

    /** Command to list all tasks. */
    public static final String LIST_COMMAND = "list";

    /** Command to mark a task as done. */
    public static final String MARK_COMMAND = "mark";

    /** Command to unmark a task as not done. */
    public static final String UNMARK_COMMAND = "unmark";

    /** Command to add a to-do task. */
    public static final String TODO_COMMAND = "todo";

    /** Command to add a deadline task. */
    public static final String DEADLINE_COMMAND = "deadline";

    /** Command to add a deadline task. */
    public static final String EVENT_COMMAND = "event";

    /** Command to delete a task. */
    public static final String DELETE_COMMAND = "delete";

    /** Command to show tasks based on a given date. */
    public static final String SHOW_COMMAND = "show";

    /** Command to find tasks based on a search phrase. */
    public static final String FIND_COMMAND = "find";

    /** Index position of the command in an input list. */
    public static final int COMMAND_INDEX = 0;

    /** Index position of the task in an input list. */
    public static final int TASK_INDEX = 0;

    /** Index position of the task status in an input list. */
    public static final int TASK_STATUS_INDEX = 1;
}
