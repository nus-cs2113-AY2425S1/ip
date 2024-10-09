package bitwise.tasks;

import bitwise.constants.Icons;

/**
 * The {@code Task} class represents a generic task with a name and completion status.
 * It provides methods to manage and retrieve information about the task.
 */
public class Task {

    protected String taskName;
    protected boolean isCompleted;

    /**
     * Returns the name of the task.
     *
     * @return a {@code String} representing the name of the task
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Sets the name of the task to the specified value.
     *
     * @param taskName a {@code String} representing the new name of the task
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Constructs a new {@code Task} with the specified name and sets its completion status to false.
     *
     * @param taskName a {@code String} representing the name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Returns a string representation of the task's completion status.
     *
     * @return a {@code String} representing the task's completion icon
     */
    public String getStatusIcon() {
        return (isCompleted ? Icons.ICON_COMPLETED : Icons.ICON_NOT_COMPLETED);
    }

    /**
     * Updates the completion status of the task.
     *
     * @param isCompleted a {@code boolean} indicating the new completion status of the task
     */
    public void markCompletionStatus(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Returns a string representation of the {@code Task}, including its status icon and name.
     *
     * @return a {@code String} that represents the {@code Task}
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + taskName;
    }
}
