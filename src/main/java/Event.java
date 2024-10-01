/**
 * The Event class represents a task that occurs over a specific time period.
 * It extends the Task class by adding a start time and an end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     * The format is [E] followed by the task description, status, and the event's time period.
     *
     * @return A string in the format [E][status] description (from: start time to: end time).
     */
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
