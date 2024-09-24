package tasklist.types;

public class Event extends Task {
    protected String from;
    protected String to;

    // Constructor for Event
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the start time.
     *
     * @return A string representing the start time.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns a string representation of the end time.
     *
     * @return A string representing the end time.
     */
    public String getTo() {
        return this.to;
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
        + " (from: " + from + " to: " + to + ")"; 
    }
}
