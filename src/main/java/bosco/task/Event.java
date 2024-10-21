package bosco.task;

import bosco.parser.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Represents an Event, a subclass of Task.
 * An <code>Event</code> object also contains
 * a start time and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Class constructor.
     *
     * @param description Description of this Event.
     * @param isDone Whether this Event is done.
     * @param from Start time of this Event.
     * @param to End time of this Event.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of this Event.
     *
     * @return String of the start time.
     */
    public String getFrom() {
        return DateTimeParser.formatDateTime(from);
    }

    /**
     * Gets the end time of this Event.
     *
     * @return String of the end time.
     */
    public String getTo() {
        return DateTimeParser.formatDateTime(to);
    }

    /**
     * Overrides the <code>toString</code> method of Task
     * to format the string representation of this Event.
     *
     * @return Formatted string of this Event,
     * with Event indicator, start time and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
