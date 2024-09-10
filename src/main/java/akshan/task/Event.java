package akshan.task;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    protected final String start;
    protected final String end;

    /**
     * Constructs an Event task.
     *
     * @param name Name of the event.
     * @param start Date when the event begins.
     * @param end Date when the event ends.
     * @throws IllegalArgumentException If name, start, or end is null or empty.
     */
    public Event(String name, String start, String end) throws IllegalArgumentException {
        super(name);
        if (start == null || start.trim().isEmpty() || end == null || end.trim().isEmpty()) {
            throw new IllegalArgumentException("Event start and end dates cannot be null or empty.");
        }
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
