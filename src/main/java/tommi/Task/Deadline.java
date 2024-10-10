package tommi.Task;

/**
 * Represents a Deadline that is a child class of Task. Contains a
 * by string with the user input time deadline is due
 */
public class Deadline extends Task{

    protected String by;

    public Deadline(boolean isDone, String taskName, String by) {
        super(isDone, taskName);
        this.by = by;
    }

    /**
     * Returns a new copy of Deadline object with updated isDone value
     *
     * @param newIsDone new isDone value
     * @return New copy of Deadline with updated isDone
     */
    @Override
    public Deadline updateIsDone(boolean newIsDone) {
        return new Deadline(newIsDone, this.taskName, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
