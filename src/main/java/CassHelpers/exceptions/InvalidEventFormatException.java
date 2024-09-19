package CassHelpers.exceptions;

public class InvalidEventFormatException extends RuntimeException {
    public InvalidEventFormatException(String message) {
        super(message);
    }
}
