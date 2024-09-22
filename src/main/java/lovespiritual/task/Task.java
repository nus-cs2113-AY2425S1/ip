package lovespiritual.task;

public class Task {
    public String description;
    public boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public void mark() {
        isMarked = true;
    }

    public void unmark() {
        isMarked = false;
    }

    @Override
    public String toString() {
        return (isMarked ? "[X] " : "[ ] ") + description;
    }
}
