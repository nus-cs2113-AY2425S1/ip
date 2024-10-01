package aether.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a specific start and end time.
 * <p>
 * The {@code Event} class extends the {@code Task} class by adding start and end times.
 * It supports multiple date and time formats for parsing user input and provides methods
 * to serialize and display the event information.
 * </p>
 */
public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    // Supported date and time formats
    private static final DateTimeFormatter[] SUPPORTED_FORMATS = {
            DateTimeFormatter.ofPattern("d/M/yyyy Hmm"),      // 2/12/2019 1800
            DateTimeFormatter.ofPattern("dd/MM/yyyy Hmm"),    // 02/12/2019 1800
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),    // 2/12/2019 18:00
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),  // 02/12/2019 18:00
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),  // 2019-12-02 18:00
    };

    // Basic format for displaying the date without ordinal
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMMM yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("h:mma");

    /**
     * Constructs an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event as a string.
     * @param to          The end time of the event as a string.
     */
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
     * Serializes the {@code Event} task into a string format suitable for storage.
     *
     * @return A string representation of the event task for storage purposes.
     */
    @Override
    public String toDataString() {
        // Store the parsed date/time or fallback to original 'from' and 'to' strings
        return (fromDateTime != null && toDateTime != null)
                ? "E | " + getStatusForStorage() + " | " + description + " | "
                + fromDateTime.format(DATE_FORMAT) + " | " + toDateTime.format(DATE_FORMAT)
                : "E | " + getStatusForStorage() + " | " + description + " | " + from + " | " + to;
    }

    /**
     * Returns a string representation of the {@code Event} task for display purposes.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        // If fromDateTime and toDateTime are valid, format them; otherwise return the strings
        return (fromDateTime != null && toDateTime != null)
                ? "[E]" + super.toString() + " (from: " + formatDateTimeWithOrdinal(fromDateTime)
                + " to: " + formatDateTimeWithOrdinal(toDateTime) + ")"
                : "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
