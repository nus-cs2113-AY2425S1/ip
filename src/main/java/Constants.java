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
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_END = "bye";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_LIST = "list";
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
}
