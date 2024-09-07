package Taylor.task;

/**
 * Represents an abstract Task. A task has a description and a completion status.
 * This class provides a base for specific types of tasks like Todo, Deadline, and Event.
 */
public abstract class Task {

    protected final String description;
    protected boolean isCompleted;

    /**
     * Constructs a Task with the specified description. The task is initially not completed.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isCompleted The completion status of the task (true if completed, false otherwise).
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param completed True if the task is completed, false otherwise.
     */
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    /**
     * Returns a string representation of the task, which includes its completion status and description.
     *
     * @return A string in the format "[ ] task description" if not completed or
     *         "[X] task description" if completed.
     */
    @Override
    public String toString() {
        return isCompleted ? "[X] " + description : "[ ] " + description;
    }

    /**
     * Abstract method to return a string suitable for saving the task to a file.
     * Each subclass should implement this method to return a string representation
     * of the task that is specific to the task type.
     *
     * @return A string representing the task suitable for file storage.
     */
    public abstract String write();
}