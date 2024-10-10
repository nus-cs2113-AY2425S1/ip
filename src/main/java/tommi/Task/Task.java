package tommi.Task;

/**
 * Represents a parent class of Task to use in TaskList. All tasks
 * contain an isDone to check if it is done and a taskName for user to read
 */
public abstract class Task {

    protected final boolean isDone;
    protected final String taskName;

    public Task(boolean isDone, String taskName) {
        this.isDone = isDone;
        this.taskName = taskName;
    }

    /**
     * Abstract updateIsDone method to be overwritten in child classes
     *
     * @param newIsDone new isDone value
     * @return New copy of Task with updated isDone
     */
    public abstract Task updateIsDone(boolean newIsDone);

    @Override
    public String toString() {
        return ("[" + (isDone ? "X" : " ") + "] " + taskName);
    }
}
