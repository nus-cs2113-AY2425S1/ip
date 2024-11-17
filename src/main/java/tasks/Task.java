package tasks;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new instance of the Task object with the given description.
     *
     * @param description description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the Task.
     *
     * @return String showing if the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUnDone() {
        isDone = false;
    }

    /**
     * Gets a description of the Task.
     *
     * @return String with description of the Task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return String representation of the Task
     */
    public String toString() {
        return getStatusIcon() + description;
    }
}