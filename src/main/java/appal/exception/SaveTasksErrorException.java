package appal.exception;

public class SaveTasksErrorException extends AppalException {
    public static final String SAVE_TASKS_ERROR_MESSAGE = "Oh no...Something went wrong while saving your tasks :(";

    public SaveTasksErrorException() {
        super(SAVE_TASKS_ERROR_MESSAGE);
    }
}
