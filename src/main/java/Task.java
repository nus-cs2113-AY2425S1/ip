public class Task {
    private boolean isDone;
    private String task;

    public Task(String task) {
        this(task, false);
    }
    public Task(String task, boolean isDone) {
        this.task = task.strip();
        this.isDone = isDone;
    }

    public String getTask() {
        return task;
    }

    public boolean isDone() {
        return isDone;
    }

    public void mark(){
        isDone = true;
    }

    public void unmark(){
        isDone = false;
    }

    public String toString(){
        return "[" +
                (isDone ? "X" : " ") +
                "] " +
                task;
    }

}
