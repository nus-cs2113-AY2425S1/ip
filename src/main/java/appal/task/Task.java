package appal.task;

public class Task {
    protected String task;
    protected int id;
    protected boolean isDone;
    private static int totalTasks = 0;

    public Task(String task) {
        setTask(task);
        setDone(false);
        totalTasks += 1;
        setId(totalTasks);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getStatusValue() {
        return (isDone ? "1" : "0");
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getTask();
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public static int getTotalTasks() {
        return totalTasks;
    }

    public static void setTotalTasks(int totalTasks) {
        Task.totalTasks = totalTasks;
    }

    public String getTaskInfo() {
        return task;
    }
}
