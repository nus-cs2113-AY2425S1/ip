package nova.exception;

/**
 * Signals that the user provides invalid input.
 */
public class InvalidInputException extends RuntimeException {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
