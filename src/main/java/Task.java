public class Task {
    private String taskName;
    private Boolean isDone;

    public Task(String taskName, Boolean isDone) {
        setTaskName(taskName);
        setIsDone(isDone);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }
}
