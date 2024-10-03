package tars.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Default constructor for Task.
     */
    public Task() {}

    /**
     * Creates a task with the given description and marks it as not done.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task status icon.
     *
     * @return "X" if the task is completed, otherwise a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");  // Return "X" if completed, otherwise space
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return Task information formatted as a string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the task's save format string.
     * Subclasses are expected to override this method.
     *
     * @return Task in string format suitable for saving.
     */
    public String toSaveFormat() {
        return description;  // Subclasses will override this method
    }
}
