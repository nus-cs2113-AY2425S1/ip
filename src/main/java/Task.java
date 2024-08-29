public class Task {
    private static final String COMPLETED_ICON = "X";
    private static final String NOT_COMPLETED_ICON = " ";
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
        return (isCompleted ? COMPLETED_ICON : NOT_COMPLETED_ICON);
    }

    public void markCompletionStatus(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
