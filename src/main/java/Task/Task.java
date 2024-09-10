package Task;

public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCounter = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCounter++;
    }

    public String getDescription() {
        return description;
    }

    public static int getTaskCounter() {
        return taskCounter;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void setDone(boolean status) {
        this.isDone = status;
    }
}
