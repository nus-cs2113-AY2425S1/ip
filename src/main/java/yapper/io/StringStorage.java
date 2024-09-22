package yapper.io;

// TODO: make more conversational
// Contains strings for the Yapper program
public class StringStorage {
    // List Array   is 0-indexed, i.e. 0, 1, ...
    // List Display is 1-indexed, i.e. 1, 2, ...
    // User Input Ordinal == Internal Use Ordinal + 1
    public static final int INDEX_OFFSET = 1;

    // For User Input-Output
    public static final String     LIST_INSTRUCTION_PREFIX = "list";
    public static final String     TODO_INSTRUCTION_PREFIX = "todo";
    public static final String DEADLINE_INSTRUCTION_PREFIX = "deadline";
    public static final String    EVENT_INSTRUCTION_PREFIX = "event";
    public static final String   DELETE_INSTRUCTION_PREFIX = "delete";
    public static final String     MARK_INSTRUCTION_PREFIX = "mark";
    public static final String   UNMARK_INSTRUCTION_PREFIX = "unmark";
    static String DEADLINE_END_DATE_DELIMITER = "/by";
    static String  EVENT_START_DATE_DELIMITER = "/from";
    static String    EVENT_END_DATE_DELIMITER = "/to";
    // For File Input-Output
    public static final String NOT_DONE_SYMBOL = " ";
    public static final String  IS_DONE_SYMBOL = "X";
    public static final String     TODO_SYMBOL = "T";
    public static final String DEADLINE_SYMBOL = "D";
    public static final String    EVENT_SYMBOL = "E";
    public static final String STORAGE_DELIMITER = " | ";
    public static String[] splitByDelimiter(String taskAsString) {
        return taskAsString.split(" \\| ");
    }

    // Text Delimiter
    public static final String LINE_DIVIDER =
            "____________________________________________________________";
    public static final String LINE_DIVIDER_INPUT =
            "_____________________ USER INPUT BELOW _____________________";
    public static final String LINE_DIVIDER_OUTPUT =
            "___________________ PROGRAM OUTPUT BELOW ___________________";
    public static void printWithDividers(String message) {
        System.out.println(LINE_DIVIDER_OUTPUT);
        System.out.println(message);
        System.out.println(LINE_DIVIDER_INPUT);
    }

    // Program Strings that are full Messages
    public static final String HELP_MESSAGE =
            "To jog your memory, here's what we can discuss: \n"
            // Nullary Argument Commands
            + "list" + ", if you forgot what you said. \n"
            + "bye"  + ", if you want me to stop yappin. "
            // Unary Argument Commands
            + "delete [index]" + ", if you don't want something. \n"
            +   "mark [index]" + ", if you're done with something. \n"
            + "unmark [index]" + ", if you're not done with something. \n"
            + "todo [todoDesc]" + ", to for a task with no dates. \n"
            // Binary Argument Commands
            + "deadline [deadlineDesc] /by [end]" + ", for a task with an end date. \n"
            // Trinary Argument Commands
            + "event [eventDesc] /from [start] /to [end]" + ", for a task with a start date and an end date. \n";
    public static final String START_UP_MESSAGE =
            "Wassup! \n"
            + "Ya ready for me to yap yer ear off? \n"
            + "Whatchu wanna talk about? ";
    public static final String SHUT_DOWN_MESSAGE =
            "Thanks for listenin' to my yappin'. \n"
            + "Call for me whenever ya feel like listening again. \n"
            + "Cya! ";

    // Program Strings that are part of a Message
    public static final String LIST_ALL_TASKS_STRING =
            "You don't remember your tasks? Lemme refresh your memory: ";
    public static final String TASK_ADDED_STRING =
            "Now, ya gotta do this too: ";
    public static final String TASK_DELETED_STRING =
            "Now, ya dun hafta do dis anymore: ";
    public static final String LIST_SIZE_STRING =
            "Your list is now THIS BIG: ";
    public static final String BEFORE_SAVING_STRING =
            "saving data to: "; // ok, just give me a while to recall
    public static final String AFTER_SAVING_STRING =
            "saved successfully!"; // Ok, I think I remember it all
    public static final String BEFORE_LOADING_STRING =
            "retrieving data from: "; // hold on, let me remember
    public static final String AFTER_LOADING_STRING =
            "retrieved successfully!"; // I've got it

    // Error Messages
    public static final String MISSING_DESCRIPTION_MESSAGE =
            "task description not given";
    public static final String MISSING_START_DATE_MESSAGE =
            "start date not given";
    public static final String MISSING_END_DATE_MESSAGE =
            "end date not given";
    public static final String INCOMPLETE_INSTRUCTION_MESSAGE =
            "Sorry, you cut off there. Continue what you were saying? ";
    public static final String UNRECOGNISED_INSTRUCTION_MESSAGE =
            "I dunno whatcha just said. Repeat it for me will ya? ";
    public static final String LIST_EMPTY_MESSAGE =
            "list is empty. ";
//    public static final String LIST_FULL_MESSAGE =
//            "Sorry, I ain't remembering more than this. ";
    public static final String LIST_OOB_MESSAGE =
            "given list ordinal is invalid. ";
    public static final String SAVING_ERROR_MESSAGE = "error in saving data";
    public static final String LOADING_ERROR_MESSAGE = "error in retrieving data";
}
