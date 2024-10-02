import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type "Event".
 * An Event task has a description, a start time, and an end time.
 */
public class Event extends Task {
    public LocalDateTime from;
    public LocalDateTime to;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the Event task, including its type, status, start time, and end time.
     *
     * @return A string in the format "[E][status] description (from: start time, to: end time)".
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + ", to: " + to.format(formatter) + ")";
    }

}