import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a description, start time, and end time.
 */
public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event as a LocalDateTime.
     * @param to          The end time of the event as a LocalDateTime.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, including its description, start time, and end time.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFormattedFrom() + " to: " + getFormattedTo() + ")";
    }

    /**
     * Returns a string representation of the Event task, formatted for saving to a file.
     * The start and end times are formatted as 'yyyy-MM-dd HHmm' to preserve precision.
     *
     * @return A string representation of the Event task in file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(FORMATTER) + " | " + to.format(FORMATTER);
    }

    /**
     * Formats the start time to a readable format (e.g., Dec 2 2019, 6:00 PM).
     *
     * @return The formatted start time string.
     */
    public String getFormattedFrom() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return from.format(formatter);
    }

    /**
     * Formats the end time to a readable format (e.g., Dec 2 2019, 8:00 PM).
     *
     * @return The formatted end time string.
     */
    public String getFormattedTo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return to.format(formatter);
    }
}
