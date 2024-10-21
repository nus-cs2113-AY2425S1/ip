package appal.exception;

/**
 * SaveTasksErrorException handles the error that occurs when saving current tasks into a text file,
 * and shows a message to notify user.
 */
public class SaveTasksErrorException extends AppalException {
    public static final String SAVE_TASKS_ERROR_MESSAGE = "Oh no...Something went wrong while saving your tasks :(";

    /**
     * Class constructor.
     */
    public SaveTasksErrorException() {
        super(SAVE_TASKS_ERROR_MESSAGE);
    }
}
