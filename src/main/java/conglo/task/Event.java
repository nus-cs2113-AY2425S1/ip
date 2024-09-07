package conglo.task;

/**
 * Represents an event task with a description, start date, and end date.
 */
public class Event extends Task {

    protected String start;
    protected String end;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task.
     * The format includes a label for events, the task's completion status,
     * description, and the start and end dates of the event.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
