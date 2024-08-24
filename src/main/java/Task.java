public class Task {
    private boolean isDone;
    private String task;

    Task() {
        isDone = false;
        task = null;
    }

    Task(String task) {
        this.task = task;
        isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
