public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    // Abstract method to get the task type (to be implemented by subclasses)
    public abstract String getType();

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}