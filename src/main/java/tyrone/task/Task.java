package tyrone.task;

/**
 * An abstract class representing a task.
 * This class provides basic functionality for managing task descriptions and completion status.
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
     * Gets the status icon for the task.
     *
     * @return "X" if the task is done, " " if it's not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets a string representation of the task with its status.
     *
     * @return A string in the format "[X] description" or "[ ] description",
     *         where X indicates a completed task.
     */
    public String getNameWithStatus() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Returns a string representation of the save record of the task.
     * To be overriden by child classes for each task type.
     *
     * @return A string representation of the task to record in save file.
     */
    public abstract String getSaveRecord();
}
