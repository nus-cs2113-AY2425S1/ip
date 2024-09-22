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
    public static final String BYE_MESSAGE = "-> Bye. Hope to see you again soon!";
    public static final String INVALID_COMMAND_MESSAGE = """
            -> Invalid command!
               Please enter one of the following commands:
                  list
                  mark <number>
                  unmark <number>
                  todo <description>
                  deadline <description> /by <by-date>
                  event <description> /from <from-date> /to <to-date>
                  remove <number>
                  bye
            """;
    public static final String UNMARK_MESSAGE = "-> The following task has been marked not done:";
    public static final String MARK_MESSAGE = "-> The following task has been marked done:";
    public static final String HELLO_MESSAGE = "Hello! I'm Nell!";
    public static final String PROMPT_MESSAGE = "What can I do for you?";
    public static final String LIST_TASKS_MESSAGE = "-> The tasks listed are as follows:";
    public static final String FILE_CREATE_ERROR_MESSAGE = "   Cannot find or create data file!";
    public static final String FILE_READ_ERROR_MESSAGE = "   Cannot read data file!";
    public static final String FILE_SAVE_ERROR_MESSAGE = "   Data not saved due to error";
    public static final String TASK_ADD_MESSAGE = "-> The task has been added to the list:";
    public static final String NEW_TASK_COUNT_MESSAGE = "   The list now has %d tasks";
    public static final String TASK_REMOVE_MESSAGE = "-> The following task has been removed from the list:";
    public static final String INVALID_DATE_TIME_MESSAGE = """
            -> Invalid date and time!
               Please key in dates and times in this format:
                  yyyy-MM-dd HHmm
            """;
}
