public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String time; // New field for storing time

    public Task(String description, String time) {
        this.description = description;
        this.isDone = false;
        this.time = time; // Initialize the time
    }

    public abstract String toFileString();

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description + (time != null ? " | " + time : "");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }
}