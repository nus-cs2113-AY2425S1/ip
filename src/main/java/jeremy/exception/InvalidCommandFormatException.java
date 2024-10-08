package jeremy.exception;

/**
 * Represents an exception thrown when a command is incorrectly formatted.
 */
public class InvalidCommandFormatException extends JeremyException {
    /**
     * Constructs an InvalidCommandFormatException with the specified message.
     *
     * @param message The detail message explaining what caused the exception.
     */
    public InvalidCommandFormatException(String message) {
        super(message);
    }
}
