public class TaskMarker {
    protected String description;
    protected boolean isDone;

    //Constructor for TaskMarker
    public TaskMarker(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    //Method to mark as done
    public void setAsDone() {
        this.isDone = true;
    }

    //Method to mark as undone
    public void setAsUndone() {
        this.isDone = false;
    }

    /**
     * Adds status icon and converts to a string for the task.
     *
     * @return string of either "[X] description" or "[ ] description".
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
