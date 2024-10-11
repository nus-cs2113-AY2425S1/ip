package userinteraction;

/**
 * Exception type thrown when a task number is encountered
 * which does not have a corresponding task in the task list.
 */
public class InvalidTaskNumberException extends RuntimeException {
    /**
     * A standard Exception constructor
     *
     * @param message The message generated when the exception is thrown
     */
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
