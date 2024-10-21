package appal.exception;

/**
 * EmptyTaskException handles the error when user did not input the task description,
 * and shows a message to notify user.
 */
public class EmptyTaskException extends AppalException {
    public static final String EMPTY_TASK_MESSAGE = "Please specify a task, pal!" +
            "\nExample input: [COMMAND] [TASK]";

    /**
     * Class constructor.
     */
    public EmptyTaskException() {
        super(EMPTY_TASK_MESSAGE);
    }
}
