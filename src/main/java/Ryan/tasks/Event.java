package Ryan.tasks;

/**
 * Represents an event task with a start time and an end time.
 */
public class Event extends Task {

    public static final String EVENT_TASK_TYPE = "E";
    public static final String EVENT_TASK_ICON = "[E]";

    protected String from;
    protected String to;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The event's description.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the task type, which is "E" for event tasks.
     *
     * @return A string representing the task type.
     */
    @Override
    public String getTaskType() {
        return EVENT_TASK_TYPE;
    }

    /**
     * Returns a string representation of the event task in a file-friendly format.
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFile() {
        return String.format("%s | %d | %s | %s | %s", getTaskType(), isMarked() ? MARKED_VALUE : UNMARKED_VALUE, getDescription(), from, to);
    }

    /**
     * Returns a string representation of the event task for display.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return EVENT_TASK_ICON + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
