package tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // markTask done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public abstract String toFormatFile();

    public void setIsDone(boolean isDoneUpdated){
        this.isDone = isDoneUpdated;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

}
