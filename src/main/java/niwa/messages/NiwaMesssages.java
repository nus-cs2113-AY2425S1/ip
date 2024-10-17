package niwa.messages;

/**
 * The class {@code NiwaMesssages} contains static final strings used throughout the {@code Niwa} chatbot.
 * These messages provide feedback to the user regarding the result of the commands.
 */
public class NiwaMesssages {
    public static final String MESSAGE_SAVE_COMPLETE = "Data saved! Here's the path: %s.";
    public static final String MESSAGE_SAVE_COMPLETE_DEFAULT = "Data saved!";
    public static final String MESSAGE_SAVE_FAILED = "Failed to save your data: %s.";

    public static final String MESSAGE_READ_FAILED = "Failed to read your data: %s.";
    public static final String MESSAGE_READ_DEFAULT = "---> Reading from default data file...";
    public static final String MESSAGE_READ_INFORM = "Reading from data file... %s:";

    public static final String MESSAGE_ADD_SUCCESS = "Okay, I've added your task: %s";
    public static final String MESSAGE_ADD_FAILED = "Failed to add new task: %s";
    public static final String MESSAGE_DELETE_SUCCESS = "Okay, I've deleted your task: %s";
    public static final String MESSAGE_DELETE_FAILED = "Failed to delete your task: %s";
    public static final String MESSAGE_MARK_SUCCESS = "Okay, I've marked your task as done: %s";
    public static final String MESSAGE_MARK_FAILED = "Failed to mark your task: %s";
    public static final String MESSAGE_UNMARK_SUCCESS = "Okay, I've unmarked your task: %s";
    public static final String MESSAGE_UNMARK_FAILED = "Failed to unmark your task: %s";

    public static final String MESSAGE_CLEAR_ASK = "I will delete all the task in the list, are you sure? [Y/N] ";
    public static final String MESSAGE_CLEAR_SUCCESS = "The list is cleared, bae!";
    public static final String MESSAGE_CLEAR_CANCEL = "Okay then, luckily I've asked you!";

    public static final String MESSAGE_LIST_SUCCESS = "Here's your tasks in the list!";
    public static final String MESSAGE_LIST_EMPTY = "You currently have no task in the list, add some bae!";

    public static final String MESSAGE_FIND_SUCCESS = "I've found your tasks!";
    public static final String MESSAGE_FIND_EMPTY = "No task found, try another keyword?";

    public static final String MESSAGE_PARSE_FAILED = "Failed to parse your command: %s";

    public static final String MESSAGE_LIST_SIZE_INFORM = "You currently have %d task(s) in the list.";

    public static final String SEPARATOR = "---------------------------------------------";

    public static final String HI_MESSAGE = "Hello sweeties! I'm %s!";
    public static final String HI_MESSAGE_1 = "What can I do for you? Let's chat <3";
    public static final String HI_MESSAGE_2 = "---> Type 'help' to see the guide.";

    public static final String BYE_MESSAGE = "Bye bae. Hope to see you again! Moah~";

}
