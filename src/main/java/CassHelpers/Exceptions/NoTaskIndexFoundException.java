package CassHelpers.Exceptions;

public class NoTaskIndexFoundException extends RuntimeException {
    public NoTaskIndexFoundException(String message) {
        super(message);
    }
}
