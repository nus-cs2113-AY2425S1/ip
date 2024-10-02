package lovespiritual.task;

/**
 * Represents an Event task, which has a description, start time, and end time.
 */
public class Event extends Task {
    /** Start time of the event. */
    public String from;
    /** End time of the event. */
    public String to;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the specific Event task, including the type, start time  and end time.
     *
     * @return String in the format "[E][status] description (from: start time, to: end time)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }
}
