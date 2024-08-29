public class Task {
    protected String taskName;
    protected boolean isCompleted;

    public String getTaskName() {
        return taskName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }

    public void markCompletionStatus(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
