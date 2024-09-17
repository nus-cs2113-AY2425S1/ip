public class Task {

    String taskName;
    boolean isDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void updateisDone(boolean newStatus) {
        this.isDone = newStatus;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }
}
