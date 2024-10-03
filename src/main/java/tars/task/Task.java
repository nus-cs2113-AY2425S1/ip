package tars.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {}

    // New constructor, accepts description and marks the task as not done
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Returns task status icon, "X" if completed, otherwise a space
    public String getStatusIcon() {
        return (isDone ? "X" : " ");  // Return "X" if completed, otherwise space
    }

    // Mark task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Mark task as not done
    public void markAsNotDone() {
        this.isDone = false;
    }

    // This is the format for saving to file, subclasses will override this method
    public String toSaveFormat() {
        return description;  // Subclasses will override this method
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
