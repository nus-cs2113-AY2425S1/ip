package task;

/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task's completion status.
     * An "X" indicates that the task is done; a space indicates it is not done.
     *
     * @return A string icon representing the task's done status.
     */
    public String getDoneStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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
    public void markAsUnDone() {
        isDone = false;
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return A formatted string representing the task. By default, it returns "NULL".
     */
    public String formattedTask() {
        return "NULL";
    }
}