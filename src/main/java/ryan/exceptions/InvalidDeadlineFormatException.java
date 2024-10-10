package ryan.exceptions;

/**
 * Exception thrown when a deadline task has an invalid format.
 */
public class InvalidDeadlineFormatException extends RyanException{

    /**
     * Constructs a new InvalidDeadlineFormatException with a default error message.
     *
     */
    public InvalidDeadlineFormatException() {
        super("Deadline tasks should be in the format 'description /by yyyy-mm-dd'.");
    }
}
