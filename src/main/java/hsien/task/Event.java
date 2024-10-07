package hsien.task;

/**
 * Represents an event task with a description and start and end dates.
 */
public class Event extends Task{
    protected String fromDate;
    protected String toDate;

    /**
     * Constructs an Event with the specified description, start date, and end date.
     *
     * @param description the description of the event
     * @param fromDate    the start date of the event
     * @param toDate      the end date of the event
     */
    public Event(String description, String fromDate, String toDate){
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Retrieves the start date of the event.
     *
     * @return the start date of the event
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * Retrieves the end date of the event.
     *
     * @return the end date of the event
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * Returns a string representation of the event's status and details.
     * The status indicates whether the event is marked as complete.
     *
     * @return a formatted string of the event's status, description, and dates
     */
    @Override
    public String getStatusDescription() {
        String status = isMarked ? "X" : " ";
        return String.format("[E] [%s] %s (from: %s to: %s)", status, super.getDescription(), getFromDate(), getToDate());
    }
}
