package tars.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;  // The deadline date

    /**
     * Creates a Deadline task with the given description and due date.
     *
     * @param description Task description.
     * @param by Due date in "yyyy-MM-dd" format.
     * @throws DateTimeParseException If the due date format is invalid.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = parseDate(by);
    }

    /**
     * Creates a Deadline task with the given description, due date, and completion status.
     *
     * @param description Task description.
     * @param by Due date in "yyyy-MM-dd" format.
     * @param isDone Task completion status.
     * @throws DateTimeParseException If the due date format is invalid.
     */
    public Deadline(String description, String by, boolean isDone) throws DateTimeParseException {
        super(description);
        this.by = parseDate(by);
        this.isDone = isDone;
    }

    /**
     * Converts the date string to LocalDate.
     *
     * @param dateStr Date string in "yyyy-MM-dd" format.
     * @return Parsed LocalDate.
     * @throws DateTimeParseException If the date format is invalid.
     */
    public static LocalDate parseDate(String dateStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateStr.trim(), formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
