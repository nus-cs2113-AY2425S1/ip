/**
 * Represents a task that is to be completed.
 * <code>Task</code> contains a String attribute <code>description</code> which contains 
 * description of task.
 * <code>Task</code> contains a boolean attribute <code>isDone</code> which states whether 
 * a task is marked as completed or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return completion status of task.
     * @return a char "X" if task is marked as completed, else return a whitespace
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * @return String containing description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets status of task as completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    public String getType() {
        return null;
    }

    public String getBy() {
        return null;
    }

    public String getFrom() {
        return null;
    }

    public String getTo() {
        return null;
    }

    /**
     * Allows the status and description of the task to 
     * be printed instead of just the object's location in memory.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), this.description);
    }
}