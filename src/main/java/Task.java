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

    public void MarkAsDone() {
        this.isDone = true;
    }

    public void MarkAsNotDone() {
        this.isDone = false;
    }

    // function override
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
