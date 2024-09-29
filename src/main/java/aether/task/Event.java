package aether.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class representing a task that has a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    // Supported date and time formats
    private static final DateTimeFormatter[] SUPPORTED_FORMATS = {
            DateTimeFormatter.ofPattern("d/M/yyyy Hmm"),      // 2/12/2019 1800
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),    // 2/12/2019 18:00
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),  // 2019-12-02 18:00
    };

    public Event(String description, String from, String to) {
        super(description);
        this.fromDateTime = parseDateTime(from);  // Try to parse 'from'
        this.toDateTime = parseDateTime(to);      // Try to parse 'to'
        if (this.fromDateTime == null) {
            this.from = from;  // If parsing fails, treat 'from' as a string
        }
        if (this.toDateTime == null) {
            this.to = to;  // If parsing fails, treat 'to' as a string
        }
    }

    // Attempt to parse the input using multiple date/time formats
    private LocalDateTime parseDateTime(String dateTimeStr) {
        for (DateTimeFormatter formatter : SUPPORTED_FORMATS) {
            try {
                return LocalDateTime.parse(dateTimeStr, formatter);  // Return if parsing succeeds
            } catch (DateTimeParseException e) {
                // Ignore and continue to the next format
            }
        }
        return null;  // Return null if no formats match
    }

    @Override
    public String toDataString() {
        // Store the parsed date/time or fallback to original 'from' and 'to' strings
        return (fromDateTime != null && toDateTime != null)
                ? "E | " + getStatusForStorage() + " | " + description + " | " + fromDateTime + " | " + toDateTime
                : "E | " + getStatusForStorage() + " | " + description + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        // If fromDateTime and toDateTime are valid, format them; otherwise return the strings
        return (fromDateTime != null && toDateTime != null)
                ? "[E]" + super.toString() + " (from: " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"))
                + " to: " + toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")"
                : "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
