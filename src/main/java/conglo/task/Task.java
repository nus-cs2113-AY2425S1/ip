package conglo.task;

/**
 * Represents a task with a description and a completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description Description of the task to be created.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon representing the task's completion status.
     * "1" indicates that the task is done; "0" indicates it is not done.
     *
     * @return A status icon ("1" if done, otherwise "0").
     */
    public String getStatusIcon() {
        if (isDone) {
            return "1";
        }
        return "0";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the task in a file format.
     */
    public abstract String toFileFormat();

    /**
     * Returns the type of the task.
     */
    protected abstract String getTaskType();

    /**
     * Returns the formatted details of the task.
     */
    protected abstract String getFormattedDetails();

}
