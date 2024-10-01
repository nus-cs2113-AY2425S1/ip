package appal.exception;

/**
 * UnspecifiedDeadlineException handles the error when user did not input the Deadline task's deadline date,
 * and shows a message to notify user.
 */
public class UnspecifiedDeadlineException extends AppalException {
    public static final String UNSPECIFIED_DEADLINE_MESSAGE = "Remember to specify a deadline, pal!" +
            "\nExample input: deadline [TASK] /by [DEADLINE FORMAT yyyy-mm-dd]";

    /**
     * Class constructor.
     */
    public UnspecifiedDeadlineException() {
        super(UNSPECIFIED_DEADLINE_MESSAGE);
    }
}
