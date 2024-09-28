package CassHelpers.exceptions;

public class NoTaskContainsMatchingPromptException extends RuntimeException {
    public NoTaskContainsMatchingPromptException(String message) {
        super(message);
    }
}
