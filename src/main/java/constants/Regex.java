package constants;

/**
 * This class defines a set of regular expressions and constants
 * used for parsing and processing user input in Bento.
 * Each constant serves a specific purpose related to task management commands.
 */
public class Regex {
    /** Regular expression for matching spaces in user input. */
    public static final String SPACE_REGEX = " ";

    /** Regular expression representing an empty string. */
    public static final String EMPTY_REGEX = "";

    /** Delimiter used to separate task statuses in the save file. */
    public static final String TASK_STATUS_DELIMITER = " | ";

    /** Regular expression for matching the task status delimiter. */
    public static final String TASK_STATUS_DELIMITER_REGEX = " \\| ";

    // Deadline
    /** Index for the task name in a Deadline input list. */
    public static final int DEADLINE_NAME_INDEX = 0;

    /** Index for the deadline date in a Deadline input list. */
    public static final int DEADLINE_BY_INDEX = 1;

    /** Prefix used to denote the deadline date in user input. */
    public static final String BY_PREFIX = "/by";

    /** Regular expression for matching the "/by" prefix in user input. */
    public static final String BY_REGEX = " " + BY_PREFIX + " ";

    // Event
    /** Prefix used to denote the start date in an Event input list. */
    public static final String FROM_PREFIX = "/from";

    /** Prefix used to denote the end date in an Event input list. */
    public static final String TO_PREFIX = "/to";

    /** Indicator for a completed task. */
    public static final String TASK_DONE_INDICATOR = "1";

    /** Indicator for an incomplete task. */
    public static final String TASK_UNDONE_INDICATOR = "0";

    /** Date format used for displaying dates in Bento. */
    public static final String DISPLAY_DATE_FORMAT = "dd MMM yyyy";

    /** Date format expected from user input and for saving. */
    public static final String INPUT_DATE_FORMAT = "dd/MM/yyyy";
}
