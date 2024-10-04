package cuboyd.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates Task
     * @param description Description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets Status Icon, could be "X" or " "
     * @return Status Icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets Description
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Mark task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a String representation of the task. Includes the Status Icon and Description.
     * @return String representation of the task.
     */
    public String toString() {
        return String.format(
                "[%s] %s",
                this.getStatusIcon(),
                this.getDescription()
        );
    }
}