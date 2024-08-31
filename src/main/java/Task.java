public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getDescription() {
        return description;
    }

    protected boolean getIsDone() {
        return isDone;
    }
    protected void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
