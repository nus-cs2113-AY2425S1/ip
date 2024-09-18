package blossom.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[]"); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String toFileFormat() {
        String result = " | " + (this.isDone ? "1" : "0") + " | " + this.getDescription();
        return result;
    }
    @Override
    public String toString() {
       String result = this.getStatusIcon() + " " + this.getDescription();
       return result;
    }
}
