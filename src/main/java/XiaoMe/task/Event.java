package XiaoMe.task;

/**
 * Represents an event task with a description, start time, and end time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description the description of the task
     * @param start the start time of the event
     * @param end the end time of the event
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the start time as a String
     */
    public String getStart() {
        return start;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the end time as a String
     */
    public String getEnd() {
        return end;
    }

    /**
     * Returns a String representation of the event task,
     * including its status, description, start time, and end time.
     *
     * @return a String representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    public String saveString() {
        return "E|" + getStatusIcon() + "|" + getDescription() + "|" + getStart() + "|" + getEnd() + "\n";
    }
}
