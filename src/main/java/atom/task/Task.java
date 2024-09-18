package atom.task;

public class Task {
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
        return "-";
    }

    public String getBy() {
        return "";
    }

    public String getFrom() {
        return "";
    }

    public String getTo() {
        return "";
    }
}
