public class Task {
    protected String description;
    protected boolean isDone;
    protected static int tasksCount = 0;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        tasksCount++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }



    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}
