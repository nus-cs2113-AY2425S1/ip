package archibald.task;

/**
 * Represents a task in the Archibald application.
 * This class serves as the base class for all types of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * 
     * @return "X" if the task is done, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toSaveFormat() {
        return isDone + "|" + description;
    }
}
