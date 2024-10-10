package bron.task;

/**
 * Represents a task. A task has a description and a status indicating whether it is done or not.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task. "X" for done, " " for not done.
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task, including its status and description.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the task in a format suitable for saving to a file.
     *
     * @return A string representing the task in save format.
     */
    public String toSaveFormat() {
        return String.format("%s | %d | %s", getTaskType(), (isDone ? 1 : 0), description);
    }

    /**
     * Returns the type of the task.
     * This method is abstract and must be implemented by child classes to return the appropriate task type.
     *
     * @return A string representing the task type.
     */
    protected abstract String getTaskType();

}
