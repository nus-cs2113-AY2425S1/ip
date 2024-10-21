package cuboyd.tasks;

public class Deadline extends Task {
    protected String by;

    /**
     * Creates deadline
     * @param description Description of Deadline
     * @param by End date of deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns End date of deadline
     * @return by End date of deadline
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a String representation of the deadline.
     * Includes the Task Type ("D" for Deadline), Status Icon, Description, By date.
     * @return String representation of the deadline.
     */
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}