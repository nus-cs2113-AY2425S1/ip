package tasks;

/**
 * Abstract class representing a generic task in Bento.
 * Each task has a name and a status indicating whether it is done or not.
 * Subclasses are expected to provide task-specific implementations.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone = false;

    /**
     * Constructs a Task with the specified task name.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Returns the name of the task.
     *
     * @return The task's name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the task's name.
     *
     * @param taskName The new name for the task.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done or undone.
     *
     * @param done True to mark the task as done, false to mark it as undone.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns a string marker indicating whether the task is done or not.
     * A done task is marked with "[x]", and an undone task is marked with "[ ]".
     *
     * @return The done marker as a string.
     */
    public String getDoneMarker() {
        // Recommended by TA to use a ternary operator

        return (isDone) ? "[x]" : "[ ]";
    }

    /**
     * Returns a string representation of the task, including its done marker and task name.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("%s %s", getDoneMarker(), getTaskName());
    }

    /**
     * Abstract method to return the task in a command-friendly format for saving or processing.
     * Subclasses must provide their own implementation for how the task should be represented as a command.
     *
     * @return The task as a command string.
     */
    public abstract String getTaskAsCommand();
}
