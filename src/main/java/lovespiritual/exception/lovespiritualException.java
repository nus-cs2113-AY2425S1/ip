package lovespiritual.exception;

/**
 * Represents an exception specific to the application.
 * Thrown when an error occurs in the logic or the user input.
 */
public class lovespiritualException extends Exception {
    /**
     * Constructs a new lovespiritualException with a specified error message.
     *
     * @param message Custom error message that explains the reason for the error.
     */
    public lovespiritualException(String message) {
        super(message);
    }
}