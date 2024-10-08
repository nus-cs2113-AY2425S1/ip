package exception;

public class InvalidCommandException extends SuBotException {
    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException() {
        super();
    }
}
