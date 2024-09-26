public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X, empty space otherwise.
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return "[ ][" + getStatusIcon() + "] " + getDescription();
    }

    // Base toSaveFormat method for generic tasks
    public String toSaveFormat() {
        return "  | " + (isDone ? "1" : "0") + " | " + description;
    }
}


