package task;
import exception.LeginEmptyTaskException;

public abstract class Task {
    protected boolean isDone;
    protected String task;

    public Task() {
        isDone = false;
        task = null;
    }

    public Task(String task) throws LeginEmptyTaskException {
        if (task.equals(" ") || task.isEmpty()) {
            throw new LeginEmptyTaskException();
        }
        this.task = task;
        isDone = false;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + task;
    }
}
