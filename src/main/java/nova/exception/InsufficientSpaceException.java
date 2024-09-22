package nova.exception;

/**
 * Exception thrown when there is insufficient space to add more tasks.
 * This is a runtime exception indicating that the maximum limit of tasks
 * has been reached in the task manager.
 */
public class InsufficientSpaceException extends RuntimeException {

    /**
     * Constructs a new InsufficientSpaceException with the specified detail message.
     *
     * @param message The detail message to be used for this exception.
     */
    public InsufficientSpaceException(String message) {
        super(message);
    }
}
