package task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected String dueDate;

    /**
     * Constructor for a Deadline task.
     *
     * @param description The description of the task.
     * @param dueDate     The due date of the task.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Gets the due date of the deadline.
     *
     * @return The due date.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string in the format "[D][X] description (by: dueDate)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}