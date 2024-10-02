package apsea.task;

/**
 * Represents a deadline task.
 * <code>by</code> represents the date and time the task should be completed by.
 */
public class Deadline extends Task{
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /* Constructor with completion status*/
    public Deadline(String description, boolean isDone, String by) {
        super(description);
        this.isDone = isDone;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "[by: " + by + "]";
    }

    @Override
    public String toFile() {
        return "D" + "; " + super.toFile() + "; " + by;
    }
}
