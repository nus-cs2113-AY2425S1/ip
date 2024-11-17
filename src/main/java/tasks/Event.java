package tasks;

public class Event extends Task {

    protected String startTime;
    protected String endTime;

    /**
     * Creates a new instance of the Event with the given description, startTime, and endTime.
     *
     * @param description description of the Event
     * @param startTime   start time of the Event
     * @param endTime     end time of the Event
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the Event.
     *
     * @return string representation of the Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
