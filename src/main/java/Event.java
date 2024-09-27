public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates event
     * @param description Description of event
     * @param from Start date of event
     * @param to End date of event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a String representation of the event.
     * Includes the Task Type ("E" for Event), Status Icon, Description, From and To date.
     * @return String representation of the event.
     */
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}