package atom.exception;

/**
 * Represents an exception when the task id specified is out of range.
 */
public class TaskIdOutOfBoundsException extends RuntimeException {

    /** Number of tasks currently in the task list */
    private int taskCount;

    public TaskIdOutOfBoundsException(int taskCount) {
        this.taskCount = taskCount;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The detailed message returned is the error message corresponding
     * to the exception.
     */
    @Override
    public String getMessage() {
        String message = "Invalid task id!!" +
                "\nYou currently have "  + taskCount + " tasks in the list.\n" +
                "\n-> Use the \"list\" command to view your current tasks.";

        return message;
    }
}
