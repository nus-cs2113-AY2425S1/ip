package aether.task;

/**
 * Task class, the base class for all types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatus() {
        return (isDone ? "✓" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }

    // Add the abstract method
    public abstract String toDataString();
}
