package Yukee.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description the description of the task
     * @param by the deadline of the task in the format "d/M/yyyy HHmm" (e.g., 2/12/2019 1800)
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
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
            System.out.println("Invalid date format. Please use the format: d/M/yyyy HHmm (e.g., 2/12/2019 1800)");
            return null;
        }
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns the deadline of the task.
     *
     * @return the LocalDateTime object representing the deadline
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + (by != null ? by.format(formatter) : "Invalid Date)") ;
    }
}
