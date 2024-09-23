package exception;

/**
 * Represents an exception thrown when the user provides an invalid deadline format to Bento.
 * This class extends {@link BentoException} and is used to notify the user when the provided
 * deadline command does not follow the expected format.
 */
public class InvalidDeadlineException extends BentoException {
    /** The default message displayed when the user provides an invalid deadline command. */
    public static final String INVALID_DEADLINE_MESSAGE = "\tHmm... That doesn't seem quite right. " +
            "Try \"deadline [DEADLINE_NAME] /by [DEADLINE_BY]\" instead!\n";

    /**
     * Constructs a new InvalidDeadlineException with the default error message.
     */
    public InvalidDeadlineException() {
        super(INVALID_DEADLINE_MESSAGE);
    }
}
