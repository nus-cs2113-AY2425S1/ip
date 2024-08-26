public class Task {
    private String taskName;
    private boolean isDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
