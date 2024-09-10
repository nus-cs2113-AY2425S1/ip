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

    public void markTaskAsDone() {
        isDone = true;
    }

    public void unmarkTaskAsDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getTask(){
        return "[" + getStatusIcon() + "] " + description;
    }

    //...
}