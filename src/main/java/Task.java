public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task() {}

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false; // Default to not done
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    // Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + taskName;
    }
}
