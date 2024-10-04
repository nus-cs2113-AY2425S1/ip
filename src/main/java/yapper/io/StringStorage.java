package yapper.io;

/**
 * Contains characters/strings for the Yapper program
 *
 * <p>
 * The {@code StringStorage} class holds various constants and utility methods
 * related to string management for user input and output.
 * <p/>
 *
 * <p>
 * Constants in this class are organized into several categories: <ul>
 *   <li><b>File Management:</b> Constants related to file paths and delimiters.</li>
 *   <li><b>User Instructions:</b> Constants representing prefixes for user commands.</li>
 *   <li><b>Task Symbols:</b> Symbols used to represent task types and statuses.</li>
 *   <li><b>Messages:</b> Predefined messages for user interaction.</li>
 *   <li><b>Exception Messages:</b> Messages used to inform users of issues or missing input.</li>
 * </ul> </p>
 *
 */
public class StringStorage {
    /**
     * An offset value used to adjust indexing for user input and display.
     *
     * <p>
     * The list array is 0-indexed (i.e. 0, 1, ...), but
     * the list display is 1-indexed (i.e. 1, 2, ...).
     * Thus, the user input ordinal = internal use ordinal + this offset value.
     * </p>
     *
     */
    public static final int INDEX_OFFSET = 1;

    /**
     * Constants representing prefixes for user commands, for User I/O.
     */
    public static final String PREFIX_HELP_INSTRUCTION = "help";
    public static final String PREFIX_FIND_INSTRUCTION = "find";
    public static final String PREFIX_LIST_INSTRUCTION = "list";
    public static final String PREFIX_TODO_INSTRUCTION = "todo";
    public static final String PREFIX_DEADLINE_INSTRUCTION = "deadline";
    public static final String PREFIX_EVENT_INSTRUCTION = "event";
    public static final String PREFIX_DELETE_INSTRUCTION = "delete";
    public static final String PREFIX_MARK_INSTRUCTION = "mark";
    public static final String PREFIX_UNMARK_INSTRUCTION = "unmark";
    public static final String PREFIX_BYE_INSTRUCTION = "bye";
    public static final String DELIMITER_DEADLINE_END_DATE = "/by";
    public static final String DELIMITER_EVENT_START_DATE = "/from";
    public static final String DELIMITER_EVENT_END_DATE = "/to";
    /**
     * Symbols used to represent task types and statuses, for File I/O.
     */
    public static final String SYMBOL_NOT_DONE = "X";
    public static final String SYMBOL_IS_DONE = "O";
    public static final String SYMBOL_TODO = "T";
    public static final String SYMBOL_DEADLINE = "D";
    public static final String SYMBOL_EVENT = "E";
    /**
     * Constants related to file management.
     */
    public static final String SAVE_FILE_PATH = "./data/savedata.txt";
    public static final String COMBINE_USING_DELIMITER = "|";
    public static final String SPLIT_USING_DELIMITER = "\\|";

    /**
     * Divides text printed to output, to distinguish between different sets of messages
     */
    public static final String LINE_DIVIDER =
            "____________________________________________________________";
    public static final String LINE_DIVIDER_INPUT =
            "_____________________ USER INPUT BELOW _____________________";
    public static final String LINE_DIVIDER_OUTPUT =
            "___________________ PROGRAM OUTPUT BELOW ___________________";
    public static final String LINE_DIVIDER_YAPPER =
            "___________________ YAPPING OUTPUT BELOW ___________________";

    /**
     * Messages that occur when no instruction can be executed.
     *
     * <p>
     * They come either when user cannot input instructions,
     * or are meant to be exhaustive.
     * Hence they can afford to be more wordy.
     * </p>
     *
     */
    public static final String HELP_MESSAGE =
            "To jog your memory, here's what we can discuss: \n"
            // Nullary Argument Commands
            + "list" + ", if you forgot what you said. \n"
            + "help" + ", if you forgot what kinda stuff we can yap about. \n"
            + "bye" + ", if you want me to stop yappin. \n"
            // Unary Argument Commands
            + "delete [index]" + ", if you don't want something. \n"
            + "mark [index]" + ", if you're done with something. \n"
            + "unmark [index]" + ", if you're not done with something. \n"
            + "find [keyword]" + ", if you're looking for something. \n"
            + "todo [todoDesc]" + ", to for a task with no dates. \n"
            // Binary Argument Commands
            + "deadline [deadlineDesc] /by [end]" + ", for a task with an end date. \n"
            // Trinary Argument Commands
            + "event [eventDesc] /from [start] /to [end]" + ", for a task with a start date and an end date. ";
    public static final String START_UP_MESSAGE =
            "Wassup! \n"
            + "Ya ready for me to yap yer ear off? \n"
            + "Whatchu wanna talk about? ";
    public static final String SHUT_DOWN_MESSAGE =
            "Thanks for listenin' to my yappin'. \n"
            + "Call for me whenever ya feel like listening again. \n"
            + "Cya! ";


    /**
     * Messages that occur when instructions are being executed.
     *
     * <p>
     * They are a small component of the program output,
     * coming before, after or between printing of tasks.
     * Hence they are less wordy.
     * </p>
     *
     */
    public static final String LIST_RELEVANT_TASKS_STRING =
            "Hmmm, let me think. What tasks could you be referring to? ";
    public static final String RELEVANT_TASKS_FOUND_STRING =
            " task(s) found contains your query string. \n"
            + LINE_DIVIDER_YAPPER + "\n"
            + "Is any of them what you were looking for? ";
    public static final String RELEVANT_TASKS_NOT_FOUND_STRING =
            "No tasks found that contains your query string. \n"
            + LINE_DIVIDER_YAPPER + "\n"
            + "Sorry, I don't recall anything that is related to that. ";
    public static final String LIST_BEFORE_STRING =
            "You're forgetting already? Lemme refresh your memory: ";
    public static final String LIST_AFTER_STRING =
            "That should be all of them. Did I forget any? ";
    public static final String ADD_BEFORE_STRING =
            "Now, I gotta remember this too: ";
    public static final String ADD_AFTER_STRING =
            "Don't worry, I've already memorized all about it! ";
    public static final String DELETE_BEFORE_STRING =
            "Now I don't need to remember this one anymore: ";
    public static final String DELETE_AFTER_STRING =
            "Don't worry, I've already forgotten about it! ";
    public static final String LIST_SIZE_STRING =
            "If I counted correctly, you have a task total of ";
    public static final String TASK_COMPLETION_STATUS_CHANGED_STRING =
            "I know, you told me that this task is ";
    public static final String TASK_IS_DONE_STRING =
            "Keep it up! You'll be done with it eventually! ";
    public static final String TASK_IS_NOT_DONE_STRING =
            "Keep going, you'll get it done eventually! ";


    /**
     * Messages that may occur when exceptions are detected during user input parsing.
     *
     * <p>
     * Only one will occur for any single line the user inputs,
     * so no likelihood of flooding the user with messages.
     * Hence they can afford to be more wordy.
     * </p>
     *
     */
    public static final String EMPTY_INPUT_MESSAGE =
            "Input is missing. \n"
            + LINE_DIVIDER_YAPPER + "\n"
            + "I can say nothing in reply. I just stare at you. \n"
            + "There is an awkward silence between us.";
    public static final String MISSING_ARGUMENTS_MESSAGE =
            "Argument(s) for instruction are missing. \n"
            + LINE_DIVIDER_YAPPER + "\n"
            + "I need more details to understand what you wanna do. ";
    public static final String MISSING_PREFIX_MESSAGE =
            "Input does not start with any of the known prefixes: "
            + PREFIX_HELP_INSTRUCTION + ", "
            + PREFIX_BYE_INSTRUCTION + ", "
            + PREFIX_FIND_INSTRUCTION + ", "
            + PREFIX_LIST_INSTRUCTION + ", "
            + PREFIX_TODO_INSTRUCTION + ", "
            + PREFIX_DEADLINE_INSTRUCTION + ", "
            + PREFIX_EVENT_INSTRUCTION + ", "
            + PREFIX_DELETE_INSTRUCTION + ", "
            + PREFIX_MARK_INSTRUCTION + ", "
            + PREFIX_UNMARK_INSTRUCTION + ", \n"
            + LINE_DIVIDER_YAPPER + "\n"
            + "Explain in a way I can understand. ";
    public static final String MISSING_QUERY_STRING_MESSAGE =
            "Query string not given. \n"
            + LINE_DIVIDER_YAPPER + "\n"
            + "What should I be finding? ";
    public static final String UNRECOGNISED_INSTRUCTION_MESSAGE =
            "Instruction is not recognised. \n"
            + LINE_DIVIDER_YAPPER + "\n"
            + "Sorry, I didn't catch what you just said. Repeat it for me will ya? ";

    /**
     * Messages that may occur when exceptions are detected after user input parsing.
     *
     * <p>
     * Only one will occur for any single line the user inputs,
     * so no likelihood of flooding the user with messages.
     * Hence they can afford to be more wordy.
     * </p>
     *
     */
    public static final String LIST_EMPTY_MESSAGE =
            "List is empty. \n"
            + LINE_DIVIDER_YAPPER + "\n"
            + "You have nothing to remember at the moment. ";
    public static final String LIST_OOB_MESSAGE =
            "Given list ordinal is invalid. \n"
            + LINE_DIVIDER_YAPPER + "\n"
            + "I don't what you're referring to. Come again? ";
    public static final String TASK_ALREADY_DONE_MESSAGE =
            "Task is already marked as done. \n"
            + LINE_DIVIDER_YAPPER + "\n"
            + "I know you've already done this. No need to tell me again. ";
    public static final String TASK_STILL_NOT_DONE_MESSAGE =
            "Task is already marked as not done. \n"
            + LINE_DIVIDER_YAPPER + "\n"
            + "I know you still haven't done this. I won't forget. ";


    /**
     * Messages that may occur when exceptions are detected during file input parsing.
     *
     * <p>
     * Each will be printed per exception detected,
     * so groups of them may flood the user with messages.
     * Hence they are less wordy.
     * </p>
     *
     */
    public static final String MISSING_DESCRIPTION_MESSAGE =
            "task description not given. "; // "You haven't told me what this is for. ";
    public static final String MISSING_START_DATE_MESSAGE =
            "Start date is not given. "; // "You haven't told me when this starts. ";
    public static final String MISSING_END_DATE_MESSAGE =
            "End date is not given. "; // "You haven't told me when this ends. ";
    public static final String INVALID_TASK_TYPE_MESSAGE =
            "Task type abbreviation is not any of these: "
            + SYMBOL_TODO + ", "
            + SYMBOL_DEADLINE + ", "
            + SYMBOL_EVENT; // "I don't remember if what kind of task this is.";
    public static final String INVALID_TASK_STATUS_MESSAGE =
            "Task completion status abbreviation is not any of these: "
            + SYMBOL_IS_DONE + ", "
            + SYMBOL_NOT_DONE; // "I don't remember if this task was done or not.";


    public static final String SAVING_ERROR_MESSAGE = // unused ?
            "error in saving data";

    /**
     * Splits a task string by a specified delimiter.
     *
     * @param taskAsString The string representation of the task to be split.
     * @return An array of strings obtained by splitting the input string.
     */
    public static String[] splitByDelimiter(String taskAsString) {
        return taskAsString.split(SPLIT_USING_DELIMITER, 3);
    }
    /**
     * Prints a message with dividers for better visibility.
     *
     * @param message The message to be printed.
     */
    public static void printWithDividers(String message) {
        System.out.println(LINE_DIVIDER_OUTPUT);
        System.out.println(message);
        System.out.println(LINE_DIVIDER_INPUT);
    }
}
