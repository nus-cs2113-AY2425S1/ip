public class Task {
    protected String description;
    protected boolean isDone;

    // Constructors
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Getters and Setters
    public void setDescription(String string) {
        this.description = string;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(Boolean status) {
        this.isDone = status;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}
