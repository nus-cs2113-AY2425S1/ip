package jeff.exception;

/**
 * The TaskDescriptionException class is a custom exception that is thrown when
 * a task's description is missing for a specific task type.
 */
public class TaskDescriptionException extends Exception{

    public TaskDescriptionException(String taskType) {
        super(taskType);
    }
}
