package tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getting status icon to show if isDone is true or false
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // markTask done task with X
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Directly manipulating protected variable to true to add its mark
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Directly manipulating protected variable to false to remove its mark
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Currently an abstract method as the way to format the task into the
     * desired format is different for all tasks.
     */
    public abstract String toFormatFile();

    public void setIsDone(boolean isDoneUpdated){
        this.isDone = isDoneUpdated;
    }

    /**
     * toString() provides the status of the task, as well as its description.
     * Meant to be overridden as other tasks that extend this have different
     * information and require different format.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

}
