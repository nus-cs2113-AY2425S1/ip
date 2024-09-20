package nell.common;

/**
 * Container for messages to be printed to user
 */
public class Messages {
    public static final String UNMARK_ERROR_MESSAGE = """
            -> Please input the command as follows:
                  unmark <task number>
            """;
    public static final String MARK_ERROR_MESSAGE = """
            -> Please input the command as follows:
                  mark <task number>
            """;
    public static final String TODO_ERROR_MESSAGE = """
            -> Please input the command as follows:
                  todo <description>
            """;
    public static final String DEADLINE_ERROR_MESSAGE = """
            -> Please input the command as follows:
                  deadline <description> /by <by-date>
            """;
    public static final String EVENT_ERROR_MESSAGE = """
            -> Please input the command as follows:
                  event <description> /from <from-date> /to <to-date>
            """;
    public static final String INVALID_TASK_MESSAGE = "-> Invalid task!";
    public static final String REMOVE_ERROR_MESSAGE = """
            -> Please input the command as follows:
                  remove <task number>
            """;
}
