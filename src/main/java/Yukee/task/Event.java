package Yukee.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the start time of the event in the format "d/M/yyyy HHmm" (e.g., 10/12/2019 0900)
     * @param to the end time of the event in the format "d/M/yyyy HHmm" (e.g., 10/12/2019 1100)
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * Parses the date and time from the input string.
     *
     * @param dateTime the date and time string to parse
     * @return the parsed LocalDateTime object, or null if parsing fails
     */
    private LocalDateTime parseDateTime(String dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use the format: d/M/yyyy HHmm (e.g., 10/12/2019 0900)");
            return null;
        }
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the start time of the event.
     *
     * @return the LocalDateTime object representing the start time
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the LocalDateTime object representing the end time
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + (from != null ? from.format(formatter) : "Invalid Date") +
                " to: " + (to != null ? to.format(formatter) : "Invalid Date") + ")";
    }
}
