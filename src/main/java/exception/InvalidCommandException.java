package exception;

/**
 * Signals that an unsupported command is requested by the user
 */
public class InvalidCommandException extends SuBotException {
    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException() {
        super();
    }
}
