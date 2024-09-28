package task;

/**
 * The Deadline class represents a deadline task.
 * It extends the abstract Task class and provides implementations for formatting the task's display output and file-saving format.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String taskName, boolean isMarked, String by) {
        super(taskName, isMarked);
        this.by = by;
    }
    public String getBy() {
        return by;
    }
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     * The string includes the "[D]", followed by the inherited task's string representation and the deadline information.
     *
     * @return A string that shows the task type, its completion status, name, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts the deadline task to a format suitable for saving to a file.
     * The format is "D | isMarked | taskName | by".
     *
     * @return A string formatted for file storage of the deadline task.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isMarked()? "1":"0") + " | " + getTaskName() + " | " + getBy();
    }
}

