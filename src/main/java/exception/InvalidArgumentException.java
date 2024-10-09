package exception;

/**
 * Signals that invalid argument(s) are founded within user's command
 */
public class InvalidArgumentException extends SuBotException {
    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException() { super(); }
}
