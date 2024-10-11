package tasktypes;

/**
 * Exception type thrown when a task is not one of the
 * existing types of task
 */
public class TaskTypeException extends Exception {
    public TaskTypeException(String errorMessage) {
        super(errorMessage);
    }
}
