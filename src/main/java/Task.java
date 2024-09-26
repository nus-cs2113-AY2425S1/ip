/**
 * The Task class represents a task with a description and a status indicating whether it is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise returns a space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X, empty space otherwise.
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, otherwise false
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return a string representing the task
     */
    public String toString() {
        return "[ ][" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns a string representation of the task in a format suitable for saving to a file.
     *
     * @return a formatted string representing the task for saving
     */
    public String toSaveFormat() {
        return "  | " + (isDone ? "1" : "0") + " | " + description;
    }
}


