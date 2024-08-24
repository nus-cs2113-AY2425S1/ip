/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    /** Task description */
    protected String description;
    /** Task completion status */
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description and mark as undone.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        setDescription(description);
        markAsUndone();
    }

    /**
     * Returns the status icon for the task.
     *
     * @return "[X]" if the task is done, "[ ]" if not
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // Mark done task with X
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
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
