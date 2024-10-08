package exception;

/**
 * Signals that invalid task number was provided in command.
 */
public class InvalidTaskNumberException extends Exception {
    /**
     * Construct InvalidTaskNumberException with defined message.
     * @param message detail of message
     */
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
