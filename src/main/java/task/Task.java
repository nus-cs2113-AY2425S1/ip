package task;

/**
 * Represents a task with a description and a completion status.
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
     * @return "X" if the task is done, otherwise " "
     */
    public String getStatusIcon() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    /**
     * Returns the description of the task.
     *
     * @return the description as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone true if the task is done, false otherwise
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a String representation of the task, including
     * its status and description.
     *
     * @return a String representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
