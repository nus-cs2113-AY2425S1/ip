package exception;

/**
 * This class represents an exception that is thrown when a command miss a specific time.
 * It extends RuntimeException and provides a custom message to indicate the nature of the error.
 */
public class IncompleteTimeException extends RuntimeException {
    public IncompleteTimeException(String message) {
        super(message);
    }

}
