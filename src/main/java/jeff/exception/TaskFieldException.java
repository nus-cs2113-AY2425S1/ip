package jeff.exception;

/**
 * The TaskFieldException class is a custom exception that is thrown when
 * a required field in a Task is missing.
 */
public class TaskFieldException extends Exception {

    public TaskFieldException(String missingField) {
        super(missingField);
    }
}
