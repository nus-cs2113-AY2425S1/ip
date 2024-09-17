package Tasks;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPrefix(){
        return "[" + this.getStatusIcon() + "]";
    }
    
    public String toString() {
        return String.format("%9s", this.getPrefix() + " | ") + this.description;
    }
}
