public class Task {
    private String item;
    private boolean isDone;

    public static int taskCount = 0;

    public Task(String item) {
        this.item = item;
        this.isDone = false;
        taskCount++;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

}
