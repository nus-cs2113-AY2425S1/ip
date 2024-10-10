public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        // Initialize a new task with a description and set it as not done
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        // Return a string representation of the task
        return "[" + getStatusIcon() + "] " + description;
    }
}
