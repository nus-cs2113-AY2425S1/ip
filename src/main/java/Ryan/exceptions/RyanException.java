package Ryan.exceptions;

/**
 * Custom exception class for handling exceptions specific to the Ryan application.
 */
public class RyanException extends Exception {
    /**
     * Constructs a new RyanException with the specified error message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public RyanException(String message) {
        super(message);
    }
}
