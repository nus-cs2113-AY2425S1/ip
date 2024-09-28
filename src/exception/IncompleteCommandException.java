package exception;

/**
 * This class represents an exception that is thrown when a command is incomplete.
 * It extends RuntimeException and provides a custom message to indicate the nature of the error.
 */
public class IncompleteCommandException extends RuntimeException {
    public static final String message = " cannot be empty! Please enter a valid command!";
    public IncompleteCommandException(String taskName) {
        super(taskName + message);
    }

}
