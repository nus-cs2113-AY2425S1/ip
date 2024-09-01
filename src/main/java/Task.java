public class Task {
    protected boolean isDone;
    protected String task;

    public Task() {
        isDone = false;
        task = null;
    }

    public Task(String task) {
        this.task = task;
        isDone = false;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + task;
    }
}
