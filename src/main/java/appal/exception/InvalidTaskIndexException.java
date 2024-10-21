package appal.exception;

/**
 * InvalidTaskException handles the error when user inputs an invalid index for tasks in the task list,
 * and shows a message to notify user.
 */
public class InvalidTaskIndexException extends AppalException {
    public static final String INVALID_TASK_INDEX_MESSAGE = "Please input a valid task number, pal!" +
            "\nType 'list' to see your tasks and each task's corresponding number!";

    /**
     * Class constructor.
     */
    public InvalidTaskIndexException() {
        super(INVALID_TASK_INDEX_MESSAGE);
    }
}
