package yapper.io;

// Contains strings for the Yapper program
public class StringStorage {
    public static final String TODO_PREFIX     = "[T]";
    public static final String DEADLINE_PREFIX = "[D]";
    public static final String EVENT_PREFIX    = "[E]";

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
            "saving data to: ";
    public static final String AFTER_SAVING_STRING =
            "saved successfully!";
    public static final String BEFORE_LOADING_STRING =
            "retrieving data from: ";
    public static final String AFTER_LOADING_STRING =
            "retrieved successfully!";

    // Error Messages
    public static final String UNRECOGNISED_INSTRUCTION_MESSAGE =
            "I dunno whatcha just said. Repeat it for me will ya? ";
    public static final String INCOMPLETE_INSTRUCTION_MESSAGE =
            "Sorry, you cut off there. Continue what you were saying? ";
    public static final String LIST_EMPTY_MESSAGE =
            "list is empty. ";
//    public static final String LIST_FULL_MESSAGE =
//            "Sorry, I ain't remembering more than this. ";
    public static final String LIST_OOB_MESSAGE =
            "given list ordinal is invalid. ";
    public static final String SAVING_ERROR_MESSAGE = "error in saving data";
    public static final String LOADING_ERROR_MESSAGE = "error in retrieving data";
}
