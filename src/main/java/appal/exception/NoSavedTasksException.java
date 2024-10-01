package appal.exception;

/**
 * NoSavedTasksException notifies user that there are no previously saved tasks to load into
 * the current task list
 */
public class NoSavedTasksException extends AppalException {
    public static final String NO_SAVED_TASKS_MESSAGE = "Seems like there are no saved tasks to load!" +
            "\nWe can start by adding the tasks that you wanna track!";

    /**
     * Class constructor.
     */
    public NoSavedTasksException() {
        super(NO_SAVED_TASKS_MESSAGE);
    }
}