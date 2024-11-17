package task;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Constructor for an Event task.
     *
     * @param description The description of the event.
     * @param startTime   The start time of the event.
     * @param endTime     The end time of the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time.
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string in the format "[E][X] description (from: startTime to: endTime)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}