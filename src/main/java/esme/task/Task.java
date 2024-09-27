package esme.task;

/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isCompleted;

    /**
     * Constructs a new task with the given description and sets the completion status to false.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean hasCompleted() {
        return this.isCompleted;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isCompleted true to mark the task as completed, false otherwise
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Returns a string representation of the task, which is in the format of
     * "[X] {description}" if the task is completed, or "[ ] {description}" if
     * the task is not completed.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        String status = isCompleted ? "[X] " : "[ ] ";
        return status + description;
    }
}
