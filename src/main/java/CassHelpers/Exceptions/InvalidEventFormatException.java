package CassHelpers.Exceptions;

public class InvalidEventFormatException extends RuntimeException {
    public InvalidEventFormatException(String message) {
        super(message);
    }
}
