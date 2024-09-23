package exception;

/**
 * Represents an exception thrown when the user provides an invalid to-do command to Bento.
 * This class extends {@link BentoException} and is used to notify the user when a to-do command
 * does not follow the expected format.
 */
public class InvalidToDoException extends BentoException {
    /** The default message displayed when the user provides an invalid to-do command. */
    public static final String INVALID_TODO_MESSAGE = "\tHmm... That doesn't seem quite right. " +
            "Try \"todo [TODO_NAME]\" instead!\n";

    /**
     * Constructs a new InvalidToDoException with the default error message.
     */
    public InvalidToDoException() {
        super(INVALID_TODO_MESSAGE);
    }
}
