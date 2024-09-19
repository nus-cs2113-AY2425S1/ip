package CassHelpers.exceptions;

public class InvalidDeadlineFormatException extends RuntimeException {
    public InvalidDeadlineFormatException(String message) {
        super(message);
    }
}
