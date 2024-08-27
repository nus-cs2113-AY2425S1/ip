public class Task {
    protected String task;
    private int id;
    protected boolean isDone;
    private static int totalTasks = 0;

    public Task(String task) {
        setTask(task);
        setDone(false);
        totalTasks += 1;
        setId(totalTasks);
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

    public void setDone(boolean done) {
        isDone = done;
    }

    public static int getTotalTasks() {
        return totalTasks;
    }
}