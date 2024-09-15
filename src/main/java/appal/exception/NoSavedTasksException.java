package appal.exception;

public class NoSavedTasksException extends AppalException {
    public static final String NO_SAVED_TASKS_MESSAGE = "Seems like there are no saved tasks to load!" +
            "\nWe can start by adding the tasks that you wanna track!";

    public NoSavedTasksException() {
        super(NO_SAVED_TASKS_MESSAGE);
    }
}