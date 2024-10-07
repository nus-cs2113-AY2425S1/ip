package pythia.task;

import pythia.utility.WriteVisitor;

import java.util.Date;

/**
 * Represents an event task that is part of the task management system.
 * An event task includes a name, a start date, and an end date, and can be marked as done or not done.
 */
public class Event extends Task {
    private String startDate;
    private Date startDateDay;
    private String endDate;
    private Date endDateDay;

    /**
     * Constructs an Event with the specified name, start date, and end date.
     * The event is initially marked as not done.
     *
     * @param name      The name of the event.
     * @param startDate The start date of the event.
     * @param endDate   The end date of the event.
     */
    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs an Event with the specified name, done status, start date, and end date.
     *
     * @param name      The name of the event.
     * @param isDone    The initial status of the event, indicating whether it is done or not.
     * @param startDate The start date of the event.
     * @param endDate   The end date of the event.
     */
    public Event(String name, boolean isDone, String startDate, String endDate) {
        super(name, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    /**
     * Returns a string representation of the event, including its status and date range.
     *
     * @return A string indicating the type of task, its status, and its date range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + startDate + " to " + endDate + ")";
    }

    /**
     * Accepts a {@link WriteVisitor} to perform a write operation on the event.
     *
     * @param visitor The visitor that handles writing the event's data to a storage format.
     * @return A string representation of the event's data formatted for saving.
     */
    @Override
    public String accept(WriteVisitor visitor) {
        return visitor.visitEvent(this);
    }
}
