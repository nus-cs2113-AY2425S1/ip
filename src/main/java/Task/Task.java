package Task;

/**
 * Represents an abstract task. A <code>Task</code> object contains a description
 * of the task and whether it is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description and marks it as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Converts the task details to a specific format for saving to a file.
     * This method must be implemented by subclasses.
     *
     * @param taskDescription The description of the task.
     * @param status The status of the task (done or not done).
     * @return The formatted string for file storage.
     */
    public abstract String toFile(String taskDescription, char status);

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task. "X" if the task is done, otherwise a blank space.
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string representation of the task, including the status icon and description.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Sets the status of the task to done or not done.
     *
     * @param status The status of the task (true for done, false for not done).
     */
    public void setDone(boolean status) {
        this.isDone = status;
    }
}