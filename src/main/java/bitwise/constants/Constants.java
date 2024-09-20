package bitwise.constants;

public class Constants {
    // ASCII art from https://ascii-generator.site/t/
    public static final String LOGO = " ____   _  _              _            \n"
            + "|  _ \\ (_)| |            (_)           \n"
            + "| |_) | _ | |_ __      __ _  ___   ___ \n"
            + "|  _ < | || __|\\ \\ / /| |/ __| / _ \\ \n"
            + "| |_) || || |_  \\ V  V / | |\\__ \\|  __/\n"
            + "|____/ |_| \\__|  \\_/\\_/  |_||___/ \\___|\n"
            + "                                       \n";
    public static final String SECTION_BREAK = "==================================================\n";
    public static final String LINE_BREAK = "--------------------------------------------------\n";
    public static final String INDENTATION = "        ";
    public static final int MAX_LIST_SIZE = 100;
    public static final String FILE_DIR_PATH = "data";
    public static final String FILE_NAME = "tasks.txt";
    public static final String FILE_PATH = FILE_DIR_PATH + "/" + FILE_NAME;

    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_END = "bye";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_INFIX_BY = "/by";
    public static final String COMMAND_INFIX_FROM = "/from";
    public static final String COMMAND_INFIX_TO = "/to";

    public static final String MESSAGE_WELCOME = SECTION_BREAK + INDENTATION + "Hello from\n" +
            LOGO + "\n" + INDENTATION + "How may I help you today?\n" + LINE_BREAK;
    public static final String MESSAGE_EXIT = LINE_BREAK + INDENTATION + "Bye, see you soon!\n" + SECTION_BREAK;
    public static final String MESSAGE_MARKED = "Awesome, I've marked this task as completed!";
    public static final String MESSAGE_UNMARKED = "I've added the task back in";
    public static final String MESSAGE_TASK_LIST = "Here are the tasks in your list: ";
    public static final String MESSAGE_ADDED = "Got it! Added:\n";
    public static final String MESSAGE_DELETED = "Got it! Deleted:\n";

    public static final String ICON_COMPLETED = "[X]";
    public static final String ICON_NOT_COMPLETED = "[ ]";
    public static final String ICON_EVENT = "[E]";
    public static final String ICON_TODO = "[T]";
    public static final String ICON_DEADLINE = "[D]";

    public static final String DESCRIPTION_COMMAND_MARK = INDENTATION + "mark: use this command to mark a task as completed \n" +
            INDENTATION + "command format: mark task_number \n";
    public static final String DESCRIPTION_COMMAND_UNMARK = INDENTATION + "unmark: use this command to mark a task as not completed \n" +
            INDENTATION + "command format: unmark task_number \n";
    public static final String DESCRIPTION_COMMAND_TODO = INDENTATION + "todo: use this command to add a todo item to the list of tasks \n" +
            INDENTATION + "command format: todo borrow books \n";
    public static final String DESCRIPTION_COMMAND_EVENT = INDENTATION + "event: use this command to add an event to the list of tasks \n" +
            INDENTATION + "command format: event watch movie /from 4pm /to 6pm \n";
    public static final String DESCRIPTION_COMMAND_DEADLINE = INDENTATION + "todo: use this command to add an item with a deadline to the list of tasks \n" +
            INDENTATION + "command format: deadline complete assignment /by 4pm \n";
    public static final String DESCRIPTION_COMMAND_LIST = INDENTATION + "list: use this command to display the list of tasks \n" +
            INDENTATION + "command format: list \n";
    public static final String DESCRIPTION_COMMAND_END = INDENTATION + "bye: use this command to end the chatbot conversation \n" +
            INDENTATION + "command format: bye \n";

    public static final String[] LIST_COMMANDS = {DESCRIPTION_COMMAND_LIST, DESCRIPTION_COMMAND_DEADLINE, DESCRIPTION_COMMAND_EVENT,
            DESCRIPTION_COMMAND_TODO, DESCRIPTION_COMMAND_MARK, DESCRIPTION_COMMAND_UNMARK, DESCRIPTION_COMMAND_END};

    public static final int PARSE_ICON_INDEX = 0;
    public static final int PARSE_IS_COMPLETED_INDEX = 0;
    public static final int PARSE_DESCRIPTION_START_INDEX = 4;

}
