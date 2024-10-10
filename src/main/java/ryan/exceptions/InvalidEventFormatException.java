package ryan.exceptions;

/**
 * Exception thrown when an event task has an invalid format.
 */
public class InvalidEventFormatException extends RyanException{

    /**
     * Constructs a new InvalidEventFormatException with a default error message.
     *
     */
    public InvalidEventFormatException() {
        super("Event tasks should be in the format 'description /from yyyy-mm-dd /to yyyy-mm-dd'.");
    }
}
