package constants;

public class Regex {
    public static final String SPACE_REGEX = " ";
    public static final String EMPTY_REGEX = "";
    public static final String TASK_STATUS_DELIMITER = " | ";
    public static final String TASK_STATUS_DELIMITER_REGEX = " \\| ";

    // Deadline
    public static final int DEADLINE_NAME_INDEX = 0;
    public static final int DEADLINE_BY_INDEX = 1;
    public static final String BY_PREFIX = "/by";
    public static final String BY_REGEX = " " + BY_PREFIX + " ";

    // Event
    public static final String FROM_PREFIX = "/from";
    public static final String TO_PREFIX = "/to";

    public static final String TASK_DONE_INDICATOR = "1";
    public static final String TASK_UNDONE_INDICATOR = "0";
}
