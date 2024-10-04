package Ryan.tasks;

public class Task {
    private final String description;
    private boolean isMarked = false;

    public Task(String description) {
        this.description = description;
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    public boolean isMarked() {
        return this.isMarked;
    }

    public String getDescription() {
        return this.description;
    }

    public String toFile() {
        return String.format("%s | %d | %s", getTaskType(), isMarked() ? 1 : 0, getDescription());
    }

    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return (isMarked ? "[X] " : "[ ] ") + description;
    }
}
