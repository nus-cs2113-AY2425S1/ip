package bitwise.tasks;

import bitwise.constants.Constants;

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
        return (isCompleted ? Constants.ICON_COMPLETED : Constants.ICON_NOT_COMPLETED);
    }

    public void markCompletionStatus(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + taskName;
    }
}
