/**
 * Represents a task that has a start and end time.
 * A <code>Event</code> object inherits from the <code>Task</code> object.
 * <code>Event</code> contains a constant attribute called <code>TYPE</code> which designates 
 * the type of task <code>Event</code> is.
 * <code>Event</code> contains a String attribute called <code>from<c/ode> which contains the 
 * starting time of the event.
 * <code>Event</code> contains a String attribute called <code>to<c/ode> which contains the 
 * ending time of the event.
 * @see Task
 */
public class Event extends Task {
    private final String TYPE = "E";
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a String that contains the type of task the <code>Event</code> is.
     * @return TYPE
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Returns a String that contains the starting time of the event.
     * @return deadline
     */
    @Override
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns a String that contains the ending time of the event.
     * @return deadline
     */
    @Override
    public String getTo() {
        return this.to;
    }

    /**
     * Allows the type, description, starting time and ending time of the task to 
     * be printed instead of just the object's location in memory.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + 
                String.format(" (from: %s to: %s)", from, to);
    } 
}
