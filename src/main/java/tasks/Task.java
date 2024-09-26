package tasks;

public class Task {
    public String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String taskType() {
        return "[]";
    }

    @Override
    public String toString() {
        return "[" + this.taskType() + "]" + "[" + this.getStatusIcon() + "]" + this.description;
    }
}

