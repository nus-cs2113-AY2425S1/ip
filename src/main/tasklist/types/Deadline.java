package tasklist.types;

import java.time.temporal.Temporal;

public class Deadline extends Task {
    protected Temporal byTemporal;
    protected String byString;

    // Constructor for when a date is parsed
    public Deadline(String description, Temporal by) {
        super(description);
        this.byTemporal = by;
    }

    // Constructor for when 'by' is plain string
    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
    }

    /**
     * Returns the 'by' value for a deadline task.
     * 
     * If the `byString` is not null, it returns the string. 
     * If `byString` is null, but `byDate` is set, it returns the date as a string.
     * If both are null, it throws an IllegalStateException.
     * 
     * @return The 'by' value, either as a string or a date.
     * @throws IllegalStateException if both `byString` and `byDate` are null.
     */
    public String getBy() {
        if (this.byString != null) {
            return this.byString;
        } else if (this.byTemporal != null) {
            return this.byTemporal.toString();
        } else {
            throw new IllegalStateException("Both 'byString' and 'byTemporal' are null :(");
        }
    }
    
    /**
     * Returns a string representation of the task.
     * The representation includes the status icon and the task description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
            (byTemporal != null ? byTemporal : byString) + ")";
    }
}
