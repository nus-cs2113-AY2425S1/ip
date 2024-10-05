package task;
import exception.LeginEmptyTaskException;

/**
 * Abstract class that provides methods shared across {@code Todo}, {@code Deadline} and {@code Event} classes
 */
public abstract class Task {
    protected boolean isDone;
    protected String task;

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

    public String getWriteInfo() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + task;
    }
}