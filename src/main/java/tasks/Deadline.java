package tasks;

public class Deadline extends Task {

    protected String by;

    /**
     * Creates a new instance of the Deadline object with the given description and by
     *
     * @param description description of the deadline
     * @param by          date of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline
     *
     * @return string representation of the Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
