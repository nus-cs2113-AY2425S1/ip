package exception;

/**
 * Signals that an invalid file is found during execution
 */
public class InvalidFileException extends SuBotException {
    public InvalidFileException(String message) {
        super(message);
    }

    public InvalidFileException() { super(); }
}
