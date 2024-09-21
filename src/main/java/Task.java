public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    // Converts the task to a string to save to file
    public String toFileString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }


    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
