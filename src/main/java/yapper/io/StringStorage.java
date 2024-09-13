package yapper.io;

// Contains strings for the Yapper program
public class StringStorage {
    // TODO: make more conversational
    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static final String HELP_MESSAGE = LINE_DIVIDER + "\n"
                                            + "To jog your memory, here's what we can discuss: \n"
                                            + "say todo to for a task with no dates. \n"
                                            + "say deadline for a task with an end date. \n"
                                            + "say event for a task with a start date and an end date. \n"
                                            + "say list if you forgot what you said. \n"
                                            + "say mark if you're done with something. \n"
                                            + "say unmark if you're not done with something. \n"
                                            + "say bye if you wanna stop. \n"
                                            + LINE_DIVIDER + "\n";
    public static final String START_UP_MESSAGE = LINE_DIVIDER + "\n"
                                              + "Wassup! \n"
                                              + "Ya ready for me to yap yer ear off? \n"
                                              + "Whatchu wanna talk about? \n"
                                              + LINE_DIVIDER + "\n";
    public static final String SHUT_DOWN_MESSAGE = LINE_DIVIDER + "\n"
                                               + "Thanks for listenin' to my yappin'. \n"
                                               + "Call for me whenever ya feel like listening again. \n"
                                               + "Cya! \n"
                                               + LINE_DIVIDER + "\n";
    public static final String TASK_ADDED_STRING = "Now, ya gotta do this too: ";
    public static final String LIST_ALL_TASKS_STRING = "You don't remember your tasks? Lemme refresh your memory: ";
    public static final String LIST_SIZE_STRING = "Your list is now THIS BIG: ";

    // Error Messages
    public static final String UNRECOGNISED_INSTRUCTION_MESSAGE = "I dunno whatcha just said. Repeat it for me will ya? ";
    public static final String INCOMPLETE_INSTRUCTION_MESSAGE = "Sorry, you cut off there. Continue what you were saying? ";
    public static final String LIST_EMPTY_MESSAGE = "list is empty. ";
    public static final String LIST_FULL_MESSAGE = "Sorry, I ain't remembering more than this. ";
    public static final String LIST_OOB_MESSAGE = "given list ordinal is invalid. ";
}
