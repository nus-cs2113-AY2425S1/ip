/**
 * Represents a Deadline task in the task list. A Deadline task has a description
 * and a due date or time (specified by the user).
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by          The due date or time for the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including its description
     * and due date.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the Deadline task, including its description in file format
     * and due date.
     *
     * @return A string representation of the Deadline task in file format.
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by;
    }
}
