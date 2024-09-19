package CassHelpers.exceptions;

public class TaskAlreadyUnmarkedException extends RuntimeException {
    public TaskAlreadyUnmarkedException(String message) {
        super(message);
    }
}
