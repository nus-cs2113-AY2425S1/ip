abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    public abstract String toString();
}
