package archibald.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task, which is a specific type of Task with a start and end time.
 * This class extends the Task class and provides additional functionality
 * specific to Event items.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs an Event with the given description, start time, and end time.
     *
     * @param description The description of the event
     * @param from The start time of the event
     * @param to The end time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time as a LocalDateTime
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time as a LocalDateTime
     */
    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_DATE_FORMAT) + " to: " + to.format(OUTPUT_DATE_FORMAT) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(FILE_DATE_FORMAT) + " | " + to.format(FILE_DATE_FORMAT);
    }
}
