package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent task with deadline, which includes date and time task should be completed by.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private LocalDateTime by;

    /**
     * Construct Deadline task with defined description and deadline (date/time)
     * @param description description of task
     * @param by deadline by which task should be completed (in "yyyy-MM-dd HHmm" format)
     * @throws IllegalArgumentException if date format is invalid
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd HHmm or yyyy-MM-dd'T'HH:mm.");
        }
    }

    /**
     * Returns deadline formatted for output
     * @return formatted deadline date/time as a string
     */
    public String getByFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return by.format(formatter);
    }

    /**
     * Returns deadline in the ISO-8601 format for saving file.
     * @return formatted deadline date and time in "yyyy-MM-dd HHmm" format
     */
    public String getByForSaving() {
        return by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
