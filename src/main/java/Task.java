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
}
