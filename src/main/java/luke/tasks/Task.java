package luke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;


    public String getDescription() {
        return description;
    }
    public boolean isDone() {
        return isDone;
    }

    public void setAsDone() {
        isDone = true;
    }
    public void setAsUndone() {
        isDone = false;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Task() {
        description = "";
        isDone = false;
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    // Methods for inheritance
    public String getBy() {
        return "";
    }
    public String getFrom() {
        return "";
    }
    public String getTo() {
        return "";
    }
    public String toSaveString() {
        return "";
    }
}
