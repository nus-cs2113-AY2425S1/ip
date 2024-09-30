package CassHelpers.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task of type "Event", which includes a start time and an end time.
 * It inherits from the Task class.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDateTime fromTime;
    protected LocalDateTime toTime;

    /**
     * Constructs a new Event object with the specified task name, start time, and end time.
     *
     * @param taskName The name of the Event task.
     * @param fromTime The start time of the event.
     * @param toTime The end time of the event.
     */
    public Event(String taskName, LocalDateTime fromTime, LocalDateTime toTime) {
        super(taskName);
        setFromTime(fromTime);
        setToTime(toTime);
        setFrom(this.fromTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm")));
        setTo(this.toTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm")));
    }

    public LocalDateTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalDateTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalDateTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalDateTime toTime) {
        this.toTime = toTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Returns a formatted string of the event's start time.
     *
     * @return The formatted start time.
     */
    public String getFromString() {
        return this.fromTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"));
    }

    /**
     * Returns a formatted string of the event's end time.
     *
     * @return The formatted end time.
     */
    public String getToString() {
        return this.toTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"));
    }

    /**
     * Overrides the default toString method to provide a specific format for Event tasks.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFromString() + " to: " + getToString() + ")";
    }

    /**
     * Returns the string format of the Event task to be written to a file.
     *
     * @return The writable string format of the Event task.
     */
    public String toWritableString() {
        return "E" + super.toWritableString() + "," + from + "," + to;
    }
}
