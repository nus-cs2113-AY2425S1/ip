/**
 * The Deadline class represents a task that has a specific deadline.
 * It extends the Task class by adding a due date (deadline) to the task.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task with a description and a deadline date.
     *
     * @param description The description of the task.
     * @param by          The deadline for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     * The format is [D] followed by the task description, status, and the deadline.
     *
     * @return A string in the format [D][status] description (by: deadline).
     */
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
