package CassHelpers.Exceptions;

public class TaskAlreadyMarkedException extends RuntimeException {
    public TaskAlreadyMarkedException(String message) {
        super(message);
    }
}
