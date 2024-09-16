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
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon representing the task's completion status.
     * "X" indicates that the task is done; a space indicates it is not done.
     *
     * @return A status icon ("✓" if done, otherwise "✗").
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public abstract String toFileFormat();

    protected abstract String getTaskType();

    protected abstract String getFormattedDetails();

}
