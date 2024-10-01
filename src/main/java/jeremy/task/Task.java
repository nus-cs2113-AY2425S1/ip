package jeremy.task;

public abstract class Task {
    protected final String description;
    protected boolean isDone;
    protected String icon = " ";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean contains(String argument) {
        return this.description.contains(argument);
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toStorageString() {
        return "";
    }
}
