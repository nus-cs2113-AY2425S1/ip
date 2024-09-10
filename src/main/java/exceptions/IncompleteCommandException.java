package exceptions;

public class IncompleteCommandException extends RuntimeException {
    public IncompleteCommandException(String message) {
        super(message);
    }
}
