package bob.task;

/**
 * Represents an event task with a specific start and end time.
 */
public class Event extends Task {
    private final String startOfEvent;
    private final String endOfEvent;

    /**
     * Constructs an Event task with the given description, start time and end time.
     *
     * @param description The description of the event.
     * @param startTime   The start time of the event.
     * @param endTime     The end time of the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startOfEvent = startTime;
        this.endOfEvent= endTime;
    }

    /**
     * Returns the start time of the event.
     *
     * @return A string representing the start time of the event.
     */
    public String getEventStartTime() {
        return this.startOfEvent;
    }

    /**
     * Returns the end time of the event.
     *
     * @return A string representing the end time of the event.
     */
    public String getEventEndTime() {
        return this.endOfEvent;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string that includes the type, description, start time and end time of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startOfEvent + " to: " + this.endOfEvent + ")";
    }
}