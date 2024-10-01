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
        boolean isStartValid = start != null && !start.trim().isEmpty();
        boolean isEndValid = end != null && !end.trim().isEmpty();

        if (!isStartValid || !isEndValid) {
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

    /**
     * Returns a string representation of the event task suitable to be stored in data file.
     *
     * @return String representation of the event task (storage format).
     */
    @Override
    public String toStorageString(String separator) {
        return this.type + separator + (super.isDone ? "1" : "0") + separator + this.name
                + separator + this.start + separator + this.end;
    };
}
