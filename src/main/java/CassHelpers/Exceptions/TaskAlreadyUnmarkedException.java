package CassHelpers.Exceptions;

public class TaskAlreadyUnmarkedException extends RuntimeException {
    public TaskAlreadyUnmarkedException(String message) {
        super(message);
    }
}
