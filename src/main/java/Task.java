/**
 * The abstract Task class represents a generic task in the task manager.
 * It provides common functionality such as managing the task's description and completion status.
 * Subclasses must implement the abstract getType() method to define the type of task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     * Initializes a task with a given description and marks it as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * Checks if the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done or not done.
     *
     * @param done True to mark the task as done, false to mark it as not done.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Abstract method to retrieve the type of task.
     * Subclasses must implement this method to define their specific task type.
     *
     * @return The type of task as a string.
     */
    public abstract String getType();

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return A string in the format "[X] description" if done, or "[ ] description" if not done.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}