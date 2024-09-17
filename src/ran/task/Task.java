package ran.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setAsDone() {
        isDone = true;
    }

    public void setAsUndone() {
        isDone = false;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public String dataFileInput() {
        return "";
    }
        
}
