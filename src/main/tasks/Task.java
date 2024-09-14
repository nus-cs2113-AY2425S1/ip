package tasks;
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new task with the specified description.
     * The task is initially set as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;  
    }

    /**
     * Marks the task as completed.
     * Sets the status of the task to done.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     * Sets the status of the task to undone.
     */
    public void setAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * The icon is "X" if the task is done, otherwise a space.
     *
     * @return A string representing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done task with X
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
     * Returns a string representation of the task.
     * The representation includes the status icon and the task description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}