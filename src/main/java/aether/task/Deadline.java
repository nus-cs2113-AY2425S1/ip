package aether.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class representing a task with a due date.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime dueDateTime; // Store the parsed date and time

    // Supported date and time formats
    private static final DateTimeFormatter[] SUPPORTED_FORMATS = {
            DateTimeFormatter.ofPattern("d/M/yyyy Hmm"),      // 2/12/2019 1800
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),    // 2/12/2019 18:00
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),  // 2019-12-02 18:00
    };

    public Deadline(String description, String by) {
        super(description);
        this.dueDateTime = parseDateTime(by);  // Try to parse the input
        if (this.dueDateTime == null) {
            this.by = by;  // If parsing fails, treat it as a string
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
        // If date is valid, store it as a formatted string; otherwise, store 'by' as a string
        return dueDateTime != null
                ? "D | " + getStatusForStorage() + " | " + description + " | " + dueDateTime
                : "D | " + getStatusForStorage() + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        // If date is valid, format it; otherwise, return 'by' as-is
        return dueDateTime != null
                ? "[D]" + super.toString() + " (by: " + dueDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")"
                : "[D]" + super.toString() + " (by: " + by + ")";
    }
}
