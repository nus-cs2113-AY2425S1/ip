package codecatalyst.task;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The task description.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return A string representing the Event task with its status icon, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
    }
}
