package bosco.task;

/**
 * Represents a task.
 * A <code>Task</code> object contains a description,
 * and a boolean for whether it is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs this class.
     *
     * @param description Description of this task.
     * @param isDone Whether this task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Sets this task to done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Sets this task to not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Gets the description of this task.
     *
     * @return Description of this task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the status icon of this task,
     * depending on whether it is done.
     *
     * @return  <code>"X"</code> if this task is done,
     *          <code>" "</code> otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Overrides the <code>toString</code> method to format
     * the string representation of this task.
     *
     * @return Formatted string of this task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
