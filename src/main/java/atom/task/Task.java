package atom.task;

public abstract class Task {
    protected String item;
    protected boolean isDone;

    public Task(String item) {
        this.item = item;
        this.isDone = false;
    }

    public String getItem() {
        return item;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String setTaskType() {
        return "";
    }

    public String getDueDate() {
        return "";
    }

    public String getStartDate() {
        return "";
    }

    public String getEndDate() {
        return "";
    }
}
