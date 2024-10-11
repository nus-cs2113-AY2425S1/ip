public abstract class Task {
    protected boolean isDone = false;
    protected String taskName = "";
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

    public String getTaskName() {
        return taskName;
    }

    public String getFormattedTaskInfo () {
        return formattedTaskInfo;
    }

    public boolean getIsDone () {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public abstract String toString();
}
