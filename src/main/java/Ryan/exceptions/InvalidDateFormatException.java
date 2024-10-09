package Ryan.exceptions;

/**
 * Exception thrown when an invalid date format is used.
 */
public class InvalidDateFormatException extends RyanException{

    /**
     * Constructs a new InvalidDateFormatException with a default error message.
     *
     */
    public InvalidDateFormatException() {
        super("Invalid date format. Please use yyyy-mm-dd.");
    }
}