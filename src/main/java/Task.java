/**
 * Represents a task with a description and completion status.
 */
abstract class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }
    /**
     * Returns the status icon of the task.
     *
     * @return A string representing the task status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    /**
     * Marks the task as done or not done.
     *
     * @param isDone true to mark the task as done, false otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    public abstract String toString();
    /**
     * Returns a string representation for saving the task to a file.
     *
     * @return A formatted string of the task for file storage.
     */
    public abstract String toFileString();
}
