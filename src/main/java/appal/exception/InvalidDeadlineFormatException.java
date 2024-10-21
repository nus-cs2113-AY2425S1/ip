package appal.exception;

/**
 * InvalidDeadlineException handles the error when user did not input the right format for the deadline date,
 * and shows a message to notify user.
 */
public class InvalidDeadlineFormatException extends AppalException {
    public static final String INVALID_DEADLINE_FORMAT_MESSAGE = "Oh no...I can't parse your deadline input :(" +
            "\nCheck that the date is in the form yyyy-mm-dd!";

    /**
     * Class constructor.
     */
    public InvalidDeadlineFormatException() {
        super(INVALID_DEADLINE_FORMAT_MESSAGE);
    }
}
