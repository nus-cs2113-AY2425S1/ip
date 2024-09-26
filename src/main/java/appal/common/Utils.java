package appal.common;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    // Constants for commands
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DELETE = "delete";
    public static final int COMMAND_INDEX = 0;

    // Integer constants for specific type of tasks
    public static final int TASK_INDEX = 1;
    public static final int BY_INDEX = 2;
    public static final int FROM_INDEX = 2;
    public static final int TO_INDEX = 3;
    public static final int MAX_ARGS = 4;

    // Integer constants to access string inputs
    public static final int TASK_DETAILS_START_INDEX = COMMAND_INDEX + 1;

    // String constants
    public static final String SPACE = " ";
    public static final String SLASH = "/";

    // Constants for file reading
    public static final String FILE_PATH = "./data/saved_tasks.txt";
    public static final Path FILE_DIRECTORY = Paths.get("./data");
    public static final String COMMA_SEPARATOR = ", ";
    public static final String LINE_BREAK = "\n";
    public static final String TASK_MARKED_VALUE = "1";
}
