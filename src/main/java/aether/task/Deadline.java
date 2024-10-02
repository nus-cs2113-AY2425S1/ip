package aether.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    protected ParsedDateTime dueDateTime; // Store the parsed date and/or time

    // Supported date and time formats
    private static final DateTimeFormatter[] DATETIME_FORMATS = {
            // Date and time with various separators and formats
            DateTimeFormatter.ofPattern("d/M/yyyy Hmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy Hmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("d-M-yyyy Hmm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy Hmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm"),
            // Include patterns with AM/PM
            DateTimeFormatter.ofPattern("d/M/yyyy h:mma"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy h:mma"),
            DateTimeFormatter.ofPattern("d-M-yyyy h:mma"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd h:mma"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy h:mma"),
    };

    private static final DateTimeFormatter[] DATE_FORMATS = {
            // Date only with various separators
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
    };

    private static final DateTimeFormatter[] TIME_FORMATS = {
            // Time only in various formats
            DateTimeFormatter.ofPattern("Hmm"),
            DateTimeFormatter.ofPattern("HHmm"),
            DateTimeFormatter.ofPattern("H:mm"),
            DateTimeFormatter.ofPattern("HH:mm"),
            DateTimeFormatter.ofPattern("h:mma"),
    };

    // Basic formats for displaying
    private static final DateTimeFormatter DISPLAY_DATE_FORMAT = DateTimeFormatter.ofPattern("MMMM yyyy");
    private static final DateTimeFormatter DISPLAY_TIME_FORMAT = DateTimeFormatter.ofPattern("h:mma");

    /**
     * Constructs a {@code Deadline} task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date and/or time as a string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.dueDateTime = parseDateTime(by);  // Try to parse the input
        if (this.dueDateTime == null) {
            this.by = by;  // If parsing fails, treat it as a string
        }
    }

    /**
     * Parses the input date and time string into a {@code ParsedDateTime} object.
     *
     * @param dateTimeStr The date and time string to parse.
     * @return A {@code ParsedDateTime} object if parsing is successful; {@code null} otherwise.
     */
    private ParsedDateTime parseDateTime(String dateTimeStr) {
        // Try to parse as LocalDateTime
        for (DateTimeFormatter formatter : DATETIME_FORMATS) {
            try {
                LocalDateTime dt = LocalDateTime.parse(dateTimeStr, formatter);
                return new ParsedDateTime(dt);
            } catch (DateTimeParseException e) {
                // Ignore and continue
            }
        }
        // Try to parse as LocalDate
        for (DateTimeFormatter formatter : DATE_FORMATS) {
            try {
                LocalDate date = LocalDate.parse(dateTimeStr, formatter);
                return new ParsedDateTime(date);
            } catch (DateTimeParseException e) {
                // Ignore and continue
            }
        }
        // Try to parse as LocalTime
        for (DateTimeFormatter formatter : TIME_FORMATS) {
            try {
                LocalTime time = LocalTime.parse(dateTimeStr, formatter);
                return new ParsedDateTime(time);
            } catch (DateTimeParseException e) {
                // Ignore and continue
            }
        }
        return null; // Return null if parsing fails
    }

    /**
     * Appends the ordinal suffix (e.g., "1st", "2nd", "3rd") to the day of the month.
     *
     * @param dayOfMonth The day of the month as an integer.
     * @return The day of the month with its appropriate ordinal suffix.
     */
    private static String getDayWithOrdinalSuffix(int dayOfMonth) {
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
    public static String formatDateTimeWithOrdinal(LocalDateTime dateTime) {
        String dayWithSuffix = getDayWithOrdinalSuffix(dateTime.getDayOfMonth());
        return dayWithSuffix + " of " + dateTime.format(DISPLAY_DATE_FORMAT) + ", " + dateTime.format(DISPLAY_TIME_FORMAT).toLowerCase();
    }

    /**
     * Formats the date in the desired format with ordinal suffix.
     *
     * @param date The {@code LocalDate} to format.
     * @return The formatted date string.
     */
    public static String formatDateWithOrdinal(LocalDate date) {
        String dayWithSuffix = getDayWithOrdinalSuffix(date.getDayOfMonth());
        return dayWithSuffix + " of " + date.format(DISPLAY_DATE_FORMAT);
    }

    /**
     * Formats the time in the desired format.
     *
     * @param time The {@code LocalTime} to format.
     * @return The formatted time string.
     */
    public static String formatTime(LocalTime time) {
        return time.format(DISPLAY_TIME_FORMAT).toLowerCase();
    }

    /**
     * Serializes the {@code Deadline} task into a string format suitable for storage.
     *
     * @return A string representation of the deadline task for storage purposes.
     */
    @Override
    public String toDataString() {
        return "D | " + getStatusForStorage() + " | " + description + " | "
                + (dueDateTime != null ? dueDateTime.format() : by);
    }

    /**
     * Returns a string representation of the {@code Deadline} task for display purposes.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (dueDateTime != null ? dueDateTime.format() : by) + ")";
    }

    /**
     * A helper class to hold parsed date and/or time.
     */
    private static class ParsedDateTime {
        private LocalDateTime dateTime; // Both date and time
        private LocalDate date;         // Only date
        private LocalTime time;         // Only time

        // Constructor for LocalDateTime
        public ParsedDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        // Constructor for LocalDate
        public ParsedDateTime(LocalDate date) {
            this.date = date;
        }

        // Constructor for LocalTime
        public ParsedDateTime(LocalTime time) {
            this.time = time;
        }

        /**
         * Formats the parsed date/time into a string based on what is available.
         *
         * @return The formatted date/time string.
         */
        public String format() {
            if (dateTime != null) {
                return formatDateTimeWithOrdinal(dateTime);
            } else if (date != null) {
                return formatDateWithOrdinal(date);
            } else if (time != null) {
                return formatTime(time);
            } else {
                return "";
            }
        }
    }
}
