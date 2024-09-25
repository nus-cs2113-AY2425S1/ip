package aegis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Event class represents a task that occurs during a specific period defined by a start and end time.
 * It extends the Task class and includes additional fields for the event's start (from) and end (to) times.
 */
public class Event extends Task {

    protected Object from; 
    protected Object to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     * The start and end times can be either LocalDateTime objects or strings.
     *
     * @param description The description of the event.
     * @param from        The start time of the event, either as a LocalDateTime or a string.
     * @param to          The end time of the event, either as a LocalDateTime or a string.
     */
    public Event(String description, Object from, Object to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event task into a string format suitable for saving to a file.
     * The start and end times are formatted as strings based on their type.
     *
     * @return A string representing the Event task in a file-friendly format.
     */
    public String toFileFormat() {
        String fromStr = (from instanceof LocalDateTime) ? 
                ((LocalDateTime) from).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.ENGLISH)) : from.toString();
        String toStr = (to instanceof LocalDateTime) ? 
                ((LocalDateTime) to).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.ENGLISH)) : to.toString();
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + fromStr + " | " + toStr;
    }

    /**
     * Returns a string representation of the Event task, including its type label, status, description,
     * start time, and end time.
     *
     * @return A string that represents the Event task, prefixed with "[E]" and formatted with start and end times.
     */
    @Override
    public String toString() {
        String fromStr = (from instanceof LocalDateTime) ? 
                ((LocalDateTime) from).format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a", Locale.ENGLISH)) : from.toString();
        String toStr = (to instanceof LocalDateTime) ? 
                ((LocalDateTime) to).format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a", Locale.ENGLISH)) : to.toString();
        return "[E]" + super.toString() + " (from: " + fromStr + ", to: " + toStr + ")";
    }
}
