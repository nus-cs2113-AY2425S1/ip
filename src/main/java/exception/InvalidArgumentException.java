package exception;

public class InvalidArgumentException extends SuBotException {
    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException() { super(); }
}
