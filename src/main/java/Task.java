public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setMarkAsDone() {
        isDone = true;
    }

    public void setMarkAsNotDone() {
        isDone = false;
    }
}
