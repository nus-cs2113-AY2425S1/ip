package Taylor.task;

public abstract class Task {
    protected final String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return isCompleted? "[X] " + description : "[ ] " + description;
    }

    public abstract String write();
}
