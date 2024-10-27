package app;

public class Messages {

    // Commands inputs for parsing
    final static String COMMAND_EXIT_TERRI = "BYE";
    final static String COMMAND_ADD_TODO = "TODO";
    final static String COMMAND_ADD_DEADLINE = "DEADLINE";
    final static String COMMAND_ADD_EVENT = "EVENT";
    final static String COMMAND_MARK_TASK = "MARK";
    final static String COMMAND_UNMARK_TASK = "UNMARK";
    final static String COMMAND_LIST_TASKS = "LIST";
    final static String COMMAND_DELETE_TASK = "DELETE";

    // Output related to program initialisation/exit
    final static String TERRI_EXIT_MSG = "Bye then! See ya soon!";
    final static String TERRI_LOGO = "\n" +
            "$$$$$$$$\\                               $$\\ \n" +
            "\\__$$  __|                              \\__|\n" +
            "   $$ |    $$$$$$\\   $$$$$$\\   $$$$$$\\  $$\\ \n" +
            "   $$ |   $$  __$$\\ $$  __$$\\ $$  __$$\\ $$ |\n" +
            "   $$ |   $$$$$$$$ |$$ |  \\__|$$ |  \\__|$$ |\n" +
            "   $$ |   $$   ____|$$ |      $$ |      $$ |\n" +
            "   $$ |   \\$$$$$$$\\ $$ |      $$ |      $$ |\n" +
            "   \\__|    \\_______|\\__|      \\__|      \\__|\n" +
            "                                            \n";
    final static String TERRI_WELCOME_MSG = "Heya! I'm Terri."
            + System.lineSeparator() + "I can log and manage a ton of different tasks - just use different keywords"
            + "followed by a description to tell me what to do:";


    // Generic output related to in-program user guidance
    final static String UNRECOGNISED_KEYWORD = "Hey! I don't understand that command.";
    final static String OFFER_REFRESHER = "Did you want a refresher on what tasks I can do for ya? Type Y to accept.";
    final static String REFRESHER_ACCEPTED = "Ok! Here's all the things I can do again!";
    final static String REFRESHER_DECLINED = "No? Gotcha - let me know what I can do for ya then!";
    final static String HELP_MESSAGE = "COMMANDS:"
            + System.lineSeparator() + "help : Prints this help message!"
            + System.lineSeparator() + "'todo' + (description) : logs a task with no specific due date"
            + System.lineSeparator() + "'deadline' + (description) + '/by (due date)' : logs a task due " +
            "at a specific point in time"
            + System.lineSeparator() + "'event' +  (description) + '/from (start time)' + '/to (end time)' : " +
                    "logs an event occurring during a specified period"
            + System.lineSeparator() + "'list' : Generates a list of all currently logged tasks/events, " +
                    "ordered oldest-newest. The number before each task is it's 'task number'"
            + System.lineSeparator() + "'mark' + '(task number)' : Records that a task has been completed"
            + System.lineSeparator() + "'unmark' + '(task number)' : Records that a task has not been completed"
            + System.lineSeparator() + "'delete' + '(task number)' : Deletes the indicated task from memory"
            + System.lineSeparator() + "'bye' : Exits the program";
    final static String REFRESHER_CLOSING = "So - what can I help you with today?";

    // Error messages for use in TerriExceptions
    final static String  MISSING_DUE_DATE = "You haven't provided a due date!";
    final static String  MISSING_DESCRIPTION = "Invalid input length. You gotta have a description!";
    final static String  MISSING_START_END = "You haven't provided a start/end time!";
    final static String  INCORRECT_INDEX = "That index is out of bounds!"
            + "Call 'list' to see how many tasks are in your list!";

}
