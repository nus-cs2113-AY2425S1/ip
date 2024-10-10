package nus.edu.rizzler.task;

/**
 * Represents a general task with a name and completion status.
 */
public class Task {
    private String taskName;
    private Boolean isDone;

    /**
     * Constructs a {@code Task} with the specified name and completion status.
     *
     * @param taskName The name of the task.
     * @param isDone Whether the task is marked as completed.
     */
    public Task(String taskName, Boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone The new completion status.
     */
    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task, formatted for display.
     *
     * @return A string representing the task with its status icon and name.
     */
    @Override
    public String toString() {
        String statusIcon = isDone ? "[X]" : "[ ]";
        return String.format(" %s %s", statusIcon, taskName);
    }

    /**
     * Returns a CSV-formatted string representation of the task.
     *
     * @return A CSV string representing the task's status and name.
     */
    public String toCSV() {
        return String.format("%s, %s", isDone, taskName);
    }
}
