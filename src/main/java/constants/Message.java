package constants;

/**
 * This class defines a set of constants used for displaying messages
 * in Bento. Each constant represents a different message
 * that can be used for user interactions, such as greetings, task
 * confirmations, and error notifications.
 */
public class Message {
    /** ASCII art logo for Bento. */
    public static final String LOGO = "\t  ____             _        \n"
            + "\t | __ )  ___ _ __ | |_ ___  \n"
            + "\t |  _ \\ / _ \\ '_ \\| __/ _ \\ \n"
            + "\t | |_) |  __/ | | | || (_) |\n"
            + "\t |____/ \\___|_| |_|\\__\\___/ \n"
            + "\t                            \n";

    /** Greeting message displayed at startup. */
    public static final String GREETING_MESSAGE = "\tKonichiwa! I am Bento, your personal assistant!\n\t" +
            "How can I help you with your tasks today?";

    /** Line divider message for visual clarity. */
    public static final String LINE_MESSAGE = "\t____________________________________________________________";

    /** Farewell message when Bento exits. */
    public static final String SAYONARA_MESSAGE = "\tThank you for working with me today! See you next time! Sayonara~";

    /** Confirmation message for successfully adding a task. */
    public static final String ADD_TASK_SUCCESS_MESSAGE = "\tRoger that! Successfully added task:";

    /** Confirmation message for successfully deleting a task. */
    public static final String DELETE_TASK_SUCCESS_MESSAGE = "\tThe following task has been removed successfully:";

    /** Confirmation message for successfully saving tasks. */
    public static final String SAVE_TASK_LIST_SUCCESS_MESSAGE = "\tBanzai! " +
            "I've saved all our tasks for you to work on them next time!";

    /** Message indicating the display of existing tasks. */
    public static final String EXISTING_TASKS_MESSAGE = "\tHere is the list of your existing tasks!";

    /** Message displayed when a task is not marked. */
    public static final String UNMARKED_MESSAGE = "\tMaybe you're not quite ready for the task just yet. " +
            "No worries, I'll be here to make sure you clear it.";

    /** Message displayed when a task is marked as done. */
    public static final String MARKED_MESSAGE = "\tYou've crushed this task! " +
            "I've gone ahead and marked it as done for you.";

    /** Message indicating the display of tasks of interest. */
    public static final String TASKS_OF_INTEREST_MESSAGE = "\tHere are your tasks of interest!";

    /** Message displayed when no tasks match the date of interest. */
    public static final String NO_TASK_OF_INTEREST_MESSAGE = "\tI can't seem to find any tasks that " +
            "match your date of interest!";

    /** Message displayed when task list is empty or if all tasks are complete. */
    public static final String NO_PENDING_TASKS_MESSAGE = "\tSugoi! You're all caught up it seems! " +
            "Go enjoy your freedom~";
}
