package ryan.exceptions;

/**
 * Exception thrown when an index is out of bounds.
 */
public class InvalidIndexException extends RyanException{

    /**
     * Constructs a new InvalidIndexException with a default error message.
     *
     */
    public InvalidIndexException() {
        super("Invalid task number.");
    }
}
