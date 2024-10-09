package Ryan.exceptions;

/**
 * Exception thrown when an unknown command is entered.
 */
public class UnknownCommandException extends RyanException {

    /**
     * Constructs a new InvalidIndexException with the specified unknown command.
     *
     * @param command The task line that caused the exception.
     */
    public UnknownCommandException(String command) {
        super("Unknown command: " + command);
    }
}