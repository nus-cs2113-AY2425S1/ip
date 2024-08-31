// Task features of Yapper
public class Task {
    protected String taskDesc;
    protected boolean isDone;

    // Constructor
    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isDone = false;
    }
    // Task Operations
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String taskToString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskDesc;
    }
}
