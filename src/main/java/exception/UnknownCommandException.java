package exception;

/**
 * Signals that an unrecognised command has been entered.
 */
public class UnknownCommandException extends Exception {
    /**
     * Construct UnknownCommandException with defined message
     * @param message detail of message
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}