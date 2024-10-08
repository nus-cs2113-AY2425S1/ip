package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent event task, which includes from/to date and time.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Construct Event task with defined description, start and end date/time.
     * @param description description of event task
     * @param from start date/time in "yyyy-MM-dd HHmm" format
     * @param to end date/time in "yyyy-MM-dd HHmm" format
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, FORMATTER);
            this.to = LocalDateTime.parse(to, FORMATTER);
            if (!this.to.isAfter(this.from)) {
                throw new IllegalArgumentException("The end time must be after the start time.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd HHmm or yyyy-MM-dd'T'HH:mm.");
        }
    }

    /**
     * Returns start date/time formatted for output
     * @return formatted start date/time as string
     */
    public String getFromFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return from.format(formatter);
    }

    /**
     * Returns end date/time formatted for output
     * @return formatted end date/time as string
     */
    public String getToFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return to.format(formatter);
    }

    /**
     * Returns start time in ISO-8601 format for saving to file.
     * @return formatted start date/time as string in "yyyy-MM-dd HHmm" format
     */
    public String getFromForSaving() {
        return from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns end time in ISO-8601 format for saving to file.
     * @return formatted end date/time as string in "yyyy-MM-dd HHmm" format
     */
    public String getToForSaving() {
        return to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " + from.format(outputFormatter) + ", to: " + to.format(outputFormatter) + ")";
    }
}
