public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Tasks that are done are marked with an X
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getType() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), this.description);
    }
}