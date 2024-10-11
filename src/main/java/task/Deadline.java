package task;

/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline with the specified description and due date.
     *
     * @param description the description of the deadline
     * @param by the due date of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline,
     * including its completion status, description, and due date.
     *
     * @return a string in the format [D][status] description (by: dueDate)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    /**
     * Returns the string format of the deadline for saving to a file.
     * The format is: taskType | status | description | dueDate
     *
     * @return a string for file storage
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by;
    }
}
