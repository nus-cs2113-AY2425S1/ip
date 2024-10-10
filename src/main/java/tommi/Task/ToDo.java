package tommi.Task;

/**
 * Represents a ToDo that is a child class of Task.
 */
public class ToDo extends Task {

    public ToDo(boolean isDone, String taskName) {
        super(isDone, taskName);
    }

    /**
     * Returns a new copy of ToDo object with updated isDone value
     *
     * @param newIsDone new isDone value
     * @return New copy of ToDo with updated isDone
     */
    @Override
    public ToDo updateIsDone(boolean newIsDone) {
        return new ToDo(newIsDone, this.taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
