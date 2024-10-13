package task;

/**
 * Represents an event task with a description, start date, and end date.
 */
public class Event extends Task {
    protected String startDate;
    protected String endDate;

    /**
     * Constructs an Event with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param startDate The starting date/time of the event.
     * @param endDate The ending date/time of the event.
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of the event task, including its completion status,
     * description, start date, and end date.
     *
     * @return A formatted string representation of the event task.
     */
    @Override
    public String toString() {
        return ("[E][" + getDoneStatusIcon() + "] " + description + " (from: " + startDate + " to: " + endDate + ")");
    }

    /**
     * Returns a formatted string representation of the event task for storage.
     *
     * @return A formatted string representing the event task suitable for saving to a file.
     */
    @Override
    public String formattedTask() {
        return ("E | " + getDoneStatusIcon() + " | " + description + " | " + startDate + " | " + endDate);
    }
}

