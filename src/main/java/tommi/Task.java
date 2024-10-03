package tommi;

public class Task {

    protected final boolean isDone;
    protected final String taskName;

    public Task(boolean isDone, String taskName) {
        this.isDone = isDone;
        this.taskName = taskName;
    }

    public Task updateIsDone(boolean newIsDone) {
        return new Task(newIsDone, this.taskName);
    }

    @Override
    public String toString() {
        return ("[" + (isDone ? "X" : " ") + "] " + taskName);
    }
}
