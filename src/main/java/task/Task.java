package task;

public class Task {
    protected String description;
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

    public String toFileFormat() {
        return String.format("%s | %d | %s", getTaskType(), isDone ? 1 : 0, description);
    }

    public String getTaskType() {
        return "T";
    }

    // function override
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
