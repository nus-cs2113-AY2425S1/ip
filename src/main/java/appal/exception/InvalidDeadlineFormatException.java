package appal.exception;

public class InvalidDeadlineFormatException extends AppalException {
    public static final String INVALID_DEADLINE_FORMAT_MESSAGE = "Oh no...I can't parse your deadline input :(" +
            "\nCheck that the date is in the form yyyy-mm-dd!";

    public InvalidDeadlineFormatException() {
        super(INVALID_DEADLINE_FORMAT_MESSAGE);
    }
}
