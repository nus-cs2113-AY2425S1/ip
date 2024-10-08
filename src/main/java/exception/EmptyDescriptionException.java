package exception;

/**
 * Signals that command given with missing description.
 */
public class EmptyDescriptionException extends Exception {
    /**
     * Construct EmptyDescriptionException with defined message.
     * @param message detail of message
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}