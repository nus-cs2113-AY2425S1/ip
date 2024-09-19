package CassHelpers.exceptions;

public class NoTaskIndexFoundException extends RuntimeException {
    public NoTaskIndexFoundException(String message) {
        super(message);
    }
}
