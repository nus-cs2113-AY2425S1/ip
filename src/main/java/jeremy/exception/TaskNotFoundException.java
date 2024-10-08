package jeremy.exception;

/**
 * Represents an exception thrown when a task cannot be found in the task list.
 */
public class TaskNotFoundException extends JeremyException {
    /**
     * Constructs a TaskNotFoundException with the specified error message.
     *
     * @param message The error message for the error
     */
    public TaskNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a TaskNotFoundException with the specified arguments.
     *
     * @param argument The identifier (or number) of the task that was not found.
     * @param taskListSize The total number of tasks in the list.
     */
    public TaskNotFoundException(String argument, int taskListSize) {
        super("What. There is no task " + argument + ". Try a number between 1 and " + taskListSize + ".");
    }
}
