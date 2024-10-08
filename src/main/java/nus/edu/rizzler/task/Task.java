package nus.edu.rizzler.task;

import nus.edu.rizzler.ui.Emoji;

/**
 * Represents a general task with a name and completion status.
 */
public class Task {
    private Emoji emoji = new Emoji();
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
     * Returns the status icon based on the task's completion status.
     *
     * @return A check mark if the task is done, otherwise an hourglass.
     */
    public String getStatusIcon() {
        return (isDone ? emoji.getTickEmoji() : emoji.getHourglassEmoji());
    }

    /**
     * Returns a string representation of the task, formatted for display.
     *
     * @return A string representing the task with its status icon and name.
     */
    @Override
    public String toString() {
        return String.format(" %s %s", getStatusIcon(), taskName);
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
