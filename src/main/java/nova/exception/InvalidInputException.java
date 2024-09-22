package nova.exception;

/**
 * Exception thrown when the user provides invalid input.
 * This is a runtime exception indicating that the input format or
 * parameters are not as expected for a command.
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Constructs a new InvalidInputException with the specified detail message.
     *
     * @param message The detail message to be used for this exception.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
