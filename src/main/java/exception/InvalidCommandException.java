package exception;

/**
 * Represents an exception thrown when an invalid command is issued to Bento.
 * This class extends {@link BentoException} and provides a custom message when an invalid
 * command is encountered during the Bento's operation.
 */
public class InvalidCommandException extends BentoException {
    /** The default message displayed when an invalid command is detected. */
    public static final String INVALID_COMMAND_MESSAGE = "\tHmm... Something seems wrong with your input. " +
            "Give it a closer look and try again!\n";

    /**
     * Constructs a new InvalidCommandException with the default error message.
     */
    public InvalidCommandException() {
        super(INVALID_COMMAND_MESSAGE);
    }
}
