package tasklist.types;

import java.time.temporal.Temporal;

public class Event extends Task {
    protected Temporal fromTemporal;
    protected Temporal toTemporal;
    protected String fromString;
    protected String toString;

    // Constructor for Event that accepts strings
    public Event(String description, String from, String to) {
        super(description);
        this.fromString = from;
        this.toString = to;
    }

    // Constructor for Event that accepts Temporal types
    public Event(String description, Temporal from, Temporal to) {
        super(description);
        this.fromTemporal = from;
        this.toTemporal = to;
    }

    /**
     * Returns the 'from' value for a deadline task.
     * 
     * If the `fromString` is not null, it returns the string. 
     * If `fromString` is null but `fromDateTime` is set, returns the date as string.
     * If both are null, it throws an IllegalStateException.
     * 
     * @return The 'from' value, either as a string or a date.
     * @throws IllegalStateException if `fromString` and `fromDateTime` are null.
     */
    public String getFrom() {
        if (this.fromString != null) {
            return this.fromString;
        } else if (this.fromTemporal != null) {
            return this.fromTemporal.toString();
        } else {
            throw new IllegalStateException(
                "Both 'fromString' and 'fromDateTime' are null :(");
        }
    }

    /**
     * Returns the 'to' value for a deadline task.
     * 
     * If the `toString` is not null, it returns the string. 
     * If `toString` is null but `toDateTime` is set, returns the date as string.
     * If both are null, it throws an IllegalStateException.
     * 
     * @return The 'to' value, either as a string or a date.
     * @throws IllegalStateException if `toString` and `toDateTime` are null.
     */
    public String getTo() {
        if (this.toString != null) {
            return this.toString;
        } else if (this.toTemporal != null) {
            return this.toTemporal.toString();
        } else {
            throw new IllegalStateException(
                "Both 'toString' and 'toDateTime' are null :(");
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
        // Add [E] tag for events
        return "[E]" + super.toString()
        + " (from: " + getFrom() + " to: " + getTo() + ")"; 
    }
}
