package tasks;

/**
 * The {@code Task} class represents a task with a description and a completion status.
 */
public class Task {
    public String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task based on its completion status.
     *
     * @return A string representing the status icon, "X" if the task is done, and a space if not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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
     * Returns the task type as a string.
     * This can be overridden by subclasses to provide specific task types.
     *
     * @return A string representing the task type.
     */
    public String taskType() {
        return "[]";
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string that describes the task, including its type, status, and description.
     */
    @Override
    public String toString() {
        return "[" + this.taskType() + "]" + "[" + this.getStatusIcon() + "]" + this.description;
    }
}
