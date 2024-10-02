package lovespiritual.task;

/**
 * Represents a Deadline task, which has a description and a deadline (date/time).
 */
public class Deadline extends Task {
    /** Deadline (date/time) for the task. */
    public String by;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param description Description of the task.
     * @param by Deadline (date/time) by which the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the specific Deadline task, including the type and deadline.
     *
     * @return String in the format "[D][status] description (by: deadline)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
