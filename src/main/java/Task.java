public abstract class Task {
    protected boolean isDone;
    protected String taskName;
    protected String formattedTaskInfo = "";

    public Task() {
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false; // Default to not done
    }

    public Task(boolean isDone, String formattedTaskInfo) {
        this.isDone = isDone;
        this.formattedTaskInfo = formattedTaskInfo;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public abstract String toString();
}
