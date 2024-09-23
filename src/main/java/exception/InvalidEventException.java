package exception;

/**
 * Represents an exception thrown when the user provides an invalid event format to Bento.
 * This class extends {@link BentoException} and is used to notify the user when the provided
 * event command does not follow the expected format.
 */
public class InvalidEventException extends  BentoException {
    /** The default message displayed when the user provides an invalid event command. */
    public static final String INVALID_EVENT_MESSAGE = "\tHmm... That doesn't seem quite right. " +
            "Try \"event [EVENT_NAME] /from [FROM] /to [TO]\" instead!\n";

    /**
     * Constructs a new InvalidEventException with the default error message.
     */
    public InvalidEventException() {
        super(INVALID_EVENT_MESSAGE);
    }
}
