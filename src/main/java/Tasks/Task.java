package Tasks;

/**
 * Represents a task.
 * A <code>Task</code> object corresponds to a task represented by a description and a status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Retrieves the status of the task.
     *
     * @return The status of the task.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Retrieves the status of the task.
     *
     * @return The status of the task.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the prefix of the task type.
     *
     * @return The prefix of the task type.
     */
    public String getPrefix(){
        return "[" + this.getStatusIcon() + "]";
    }
    
    /**
     * Retrieves the string representation of the task.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return String.format("%9s", this.getPrefix() + " | ") + this.description;
    }
}
