/**
 * Represents a Deadline task, which is a task that needs to be done
 * before a specific date/time.
 */
class Deadline extends Task {
    protected String by;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description A description of the task.
     * @param by The deadline by which the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}