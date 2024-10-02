package apsea.task;

/**
 * Represents a task in the task list.
 * <code>description</code> represents the description or name of the task.
 * <code>isDone</code> represents the completion status of the task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getFileStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return " [" + getStatusIcon() + "] "
            + this.description;
    }

    public String toFile() {
        return getFileStatusIcon() + "; " + this.description;
    }
}
