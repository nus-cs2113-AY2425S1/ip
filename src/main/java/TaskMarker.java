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

    //Method to convert icon to string
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
