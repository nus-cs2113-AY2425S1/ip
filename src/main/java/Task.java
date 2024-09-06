public class Task {
    private final String description;
    private boolean isMarked = false;  // Inline initialization

    public Task(String description) {
        this.description = description;
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    @Override
    public String toString() {
        return (isMarked ? "[X] " : "[ ] ") + description;
    }
}
