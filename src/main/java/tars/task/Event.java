package tars.task;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    protected String from; // Event start time
    protected String to;   // Event end time

    /**
     * Creates an Event task with the given description, start time, and end time.
     *
     * @param description Task description.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event task with the given description, start time, end time, and completion status.
     *
     * @param description Task description.
     * @param from Start time of the event.
     * @param to End time of the event.
     * @param isDone Task completion status.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
