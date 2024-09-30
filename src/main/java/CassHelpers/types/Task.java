package CassHelpers.types;

/**
 * The Task class represents a generic task with a task name and a completed status.
 * This is the base class for specific types of tasks like Todo, Deadline, and Event.
 */
public class Task {
    protected String taskName;
    protected boolean isCompleted;

    /**
     * Constructs a new Task object with the specified task name and initializes it as incomplete.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        setTaskName(taskName);
        setCompleted(false);
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the status icon based on whether the task is completed.
     *
     * @return "X" if the task is completed, otherwise " ".
     */
    public String getStatusIcon() {
        return (this.isCompleted ? "X" : " ");
    }

    /**
     * Returns the string representation of the task, including its completion status and task name.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getTaskName();
    }

    /**
     * Returns the string format of the task to be written to a file.
     *
     * @return The writable string format of the task.
     */
    public String toWritableString() {
        return "," + (this.isCompleted ? 1 : 0) + "," + this.getTaskName();
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }
}
