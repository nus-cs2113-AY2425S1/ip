package tyrone.util;

/**
 * A class to store common constants used by Parser and Storage classes.
 */
public interface TaskInputConstants {
    String EVENT_START_FLAG = " /from";
    String EVENT_END_FLAG = " /to";
    String DEADLINE_FLAG = " /by";
    int START_INDEX_OFFSET_DESCRIPTION = 1;
    int START_INDEX_OFFSET_START = 7;
    int START_INDEX_OFFSET_END = 5;
    int START_INDEX_OFFSET_DEADLINE = 5;
}
