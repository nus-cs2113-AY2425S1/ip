package task;

/**
 * Represents a task with a description and completion status (done or not done).
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the given description.
     * By default, the task is marked as not done.
     *
     * @param description A description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;  // By default, a task is not done when created
    }

    /**
     * Returns the status icon of the task.
     * "X" indicates that the task is done, and " " indicates that it is not done.
     *
     * @return A string representing the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // "X" for done, " " for not done
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the task information formatted for saving to a file.
     *
     * @return A string representation of the task formatted for file storage,
     *         or null if the implementation is not provided.
     */
    public String toFileFormat() {
        return null; // Override in subclasses for specific formatting
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task,
     * including its status and description.
     *
     * @return A string representing the task in the format "[status_icon] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}