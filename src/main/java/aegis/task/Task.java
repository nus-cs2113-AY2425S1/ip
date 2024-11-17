package aegis.task;

/**
 * The Task class represents a task with a description and a completion status.
 * It provides methods to mark the task as done or undone, retrieve task details, and format the task for file storage.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * 
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done by setting its status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done by setting its status to false.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * 
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Converts the task into a string format suitable for saving to a file.
     * 
     * @return A string representing the task in a file-friendly format.
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string that represents the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
