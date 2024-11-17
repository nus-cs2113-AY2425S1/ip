package bob.task;

/**
 * Represents a task with a description and a status indicating if it is done or not.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description. The task is initially marked as not done.
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
     * @return A string representing the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon of the task, indicating whether it is done.
     *
     * @return "X" if the task is done, otherwise a space character.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task, indicating it is not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return A string that includes the status icon and description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}