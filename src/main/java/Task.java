public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }


    public String getDescription() {
        return description;
    }

    public String currentStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return currentStatus() + " " + description;
    }

}
