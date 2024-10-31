package task;

/**
 * Represents an event task with a description, start time, and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the starting time of the event
     * @param to the ending time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event,
     * including its completion status, description, start time, and end time.
     *
     * @return a string in the format [E][status] description (from: start to: end)
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the string format of the event for saving to a file.
     * The format is: taskType | status | description | from | to
     *
     * @return a string for file storage
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + from + " | " + to;
    }
}
