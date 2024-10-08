package nova.exception;

/**
 * Signals an error when the max size of TaskList is exceeded.
 */
public class InsufficientSpaceException extends RuntimeException {
    public InsufficientSpaceException(String message) {
        super(message);
    }
}
