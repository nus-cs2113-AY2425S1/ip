package aether.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a specific due date and time.
 * <p>
 * The {@code Deadline} class extends the {@code Task} class by adding a due date and time.
 * It supports multiple date and time formats for parsing user input and provides methods
 * to serialize and display the task information.
 * </p>
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime dueDateTime; // Store the parsed date and time

    // Supported date and time formats
    private static final DateTimeFormatter[] SUPPORTED_FORMATS = {
            DateTimeFormatter.ofPattern("d/M/yyyy Hmm"),      // 2/12/2019 1800
            DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"),     // 02/12/2019 1800
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),    // 2/12/2019 18:00
            DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm"),   // 02/12/2019 18:00
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),  // 2019-12-02 18:00
    };

    // Basic format for displaying the date without ordinal
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMMM yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("h:mma");

    /**
     * Constructs a {@code Deadline} task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date and time as a string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.dueDateTime = parseDateTime(by);  // Try to parse the input
        if (this.dueDateTime == null) {
            this.by = by;  // If parsing fails, treat it as a string
        }
    }

    /**
     * Parses the input date and time string into a {@code LocalDateTime} object.
     *
     * @param dateTimeStr The date and time string to parse.
     * @return A {@code LocalDateTime} object if parsing is successful; {@code null} otherwise.
     */
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

    /**
     * Appends the ordinal suffix (e.g., "1st", "2nd", "3rd") to the day of the month.
     *
     * @param dayOfMonth The day of the month as an integer.
     * @return The day of the month with its appropriate ordinal suffix.
     */
    private String getDayWithOrdinalSuffix(int dayOfMonth) {
        if (dayOfMonth >= 11 && dayOfMonth <= 13) {
            return dayOfMonth + "th";
        }
        switch (dayOfMonth % 10) {
        case 1:  return dayOfMonth + "st";
        case 2:  return dayOfMonth + "nd";
        case 3:  return dayOfMonth + "rd";
        default: return dayOfMonth + "th";
        }
    }

    /**
     * Formats the date and time in the desired format with ordinal suffix.
     *
     * @param dateTime The {@code LocalDateTime} to format.
     * @return The formatted date and time string.
     */
    private String formatDateTimeWithOrdinal(LocalDateTime dateTime) {
        String dayWithSuffix = getDayWithOrdinalSuffix(dateTime.getDayOfMonth());
        return dayWithSuffix + " of " + dateTime.format(DATE_FORMAT) + ", " + dateTime.format(TIME_FORMAT).toLowerCase();
    }

    /**
     * Serializes the {@code Deadline} task into a string format suitable for storage.
     *
     * @return A string representation of the deadline task for storage purposes.
     */
    @Override
    public String toDataString() {
        // If date is valid, store it as a formatted string; otherwise, store 'by' as a string
        return dueDateTime != null
                ? "D | " + getStatusForStorage() + " | " + description + " | " + formatDateTimeWithOrdinal(dueDateTime)
                : "D | " + getStatusForStorage() + " | " + description + " | " + by;
    }

    /**
     * Returns a string representation of the {@code Deadline} task for display purposes.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        // If date is valid, format it; otherwise, return 'by' as-is
        return dueDateTime != null
                ? "[D]" + super.toString() + " (by: " + formatDateTimeWithOrdinal(dueDateTime) + ")"
                : "[D]" + super.toString() + " (by: " + by + ")";
    }
}
