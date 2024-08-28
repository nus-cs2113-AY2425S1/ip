public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;  // By default, a task is not done when created
    }

    // Returns status icon
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // "X" for done, " " for not done
    }

    // Marks the task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Marks the task as not done
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
