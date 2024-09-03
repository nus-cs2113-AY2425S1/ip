/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task{
    protected final String start;
    protected final String end;

    /**
     * Constructs an Event task.
     *
     * @param name Name of the event.
     * @param start Date when the event begins.
     * @param end Date when the event ends.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.type = "E";
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + (this.isDone ? "X" : " ") + "] " + this.name
                + " (from: " + this.start + " to: " + this.end + ")";
    }
}
