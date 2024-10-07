package hsien.task;

/**
 * Act as a parent class for other tasks with a description and a
 * status indicating whether it is marked as complete.
 */
public class Task {
    protected String description;
    protected boolean isMarked;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task's status and description.
     * The status shows whether the task is marked as complete.
     *
     * @return a formatted string of the task's status and description
     */
    public String getStatusDescription() {
        String status = isMarked ? "X" : " ";
        return String.format("[%s] %s", status, getDescription());
    }

    /**
     * Marks the task as complete.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Unmarks the task, setting its status to incomplete.
     */
    public void unmark() {
        this.isMarked = false;
    }
}
