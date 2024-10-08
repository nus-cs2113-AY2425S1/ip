package exception;

public class InvalidFileException extends SuBotException {
    public InvalidFileException(String message) {
        super(message);
    }

    public InvalidFileException() { super(); }
}
