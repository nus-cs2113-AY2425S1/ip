package Yukee.task;

/**
 * Represents a general task with a description and a status indicating if it is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;  // By default, the task is not done.
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise an empty space
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
     * Returns the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the task type.
     * This method is meant to be implemented by subclasses to provide their specific type.
     *
     * @return a string representing the type of the task
     */
    public abstract String getTaskType();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
