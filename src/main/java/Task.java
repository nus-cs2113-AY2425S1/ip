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

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        if(isDone) {
            System.out.println("Task is already done");
            return;
        }
        isDone = true;
    }
    public void markAsNotDone() {
        if(!isDone) {
            System.out.println("Task is already not done");
            return;
        }
        isDone = false;
    }
}
