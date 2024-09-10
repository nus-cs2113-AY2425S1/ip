package task;

/**
 * Represents an Event task, which is a task that starts and ends
 * at specific dates/times.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates a new Event task with the given description, start, and end times.
     *
     * @param description A description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
