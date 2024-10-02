package blossom.task;

/**
 * Represents a generic task with a description and a completion status.
 * This class serves as a base for more specific types of tasks such as todo, deadlines or events.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is not completed when created.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon indicating whether the task is done.
     *
     * @return A string representing the status icon ("[X]" if done, "[]" if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[]"); // mark done task with X
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description for the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as not completed.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task for file storage,
     *
     * @return The file format string of the task.
     */
    public String toFileFormat() {
        return " | " + (this.isDone ? "1" : "0") + " | " + this.getDescription();
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
