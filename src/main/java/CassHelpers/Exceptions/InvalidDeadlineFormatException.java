package CassHelpers.Exceptions;

public class InvalidDeadlineFormatException extends RuntimeException {
    public InvalidDeadlineFormatException(String message) {
        super(message);
    }
}
