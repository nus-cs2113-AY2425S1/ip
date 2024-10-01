package jeremy.exception;

/**
 * Represents an exception thrown when the provided task number is invalid or not a number.
 */
public class InvalidTaskNumberException extends JeremyException {
    /**
     * Constructs an InvalidTaskNumberException with the specified message.
     *
     * @param message The detail message explaining what caused the exception.
     */
    public InvalidTaskNumberException(String message) {
        super("Wow, " + message + " is not even a number.");
    }
}
