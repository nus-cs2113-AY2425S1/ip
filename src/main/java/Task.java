/**
 * Represents an abstract task. A task has a description and a completion status (done or not done).
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    // Constructs a Task with the specified description. The task is initially not marked as done.
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Returns the status icon of the task. If the task is done, returns "X", otherwise returns a space " ".
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // Marks the task as done.
    public void markAsDone() {
        this.isDone = true;
    }

    // Marks the task as not done.
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Converts the task details into a format suitable for saving to a file.
     * Subclasses must implement this method.
     */
    public abstract String toFileFormat();

    // Returns a string representation of the task, including its status icon and description.
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
