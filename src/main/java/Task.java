public class Task {
    protected String item;
    protected boolean isDone;

    public static int taskCount = 0;

    public Task(String item) {
        this.item = item;
        this.isDone = false;
        taskCount++;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public String getItem() {
        return item;
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

    public String setTaskType() {
        return "-";
    }

    public String getBy() {
        return "";
    }

    public String getFrom() {
        return "";
    }

    public String getTo() {
        return "";
    }
}
