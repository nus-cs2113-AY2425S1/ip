package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline.
 *
 * This class encapsulates a task description and a due date, handling different date formats.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for the Deadline task.
     * Attempts to parse the provided due date string using the default date format.
     * If the default format fails, it tries the alternate format.
     *
     * @param description The description of the deadline task.
     * @param by          The due date/time of the task in String format.
     * @throws IllegalArgumentException If both default and alternate date parsing fail.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DEFAULT_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                this.by = LocalDateTime.parse(by, ALTERNATE_FORMATTER);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Invalid date format. Please provide a valid date in the required format.");
            }
        }
    }

    /**
     * Returns the formatted due date for display.
     *
     * @return The due date in the format "MMM dd yyyy HH:mm".
     */
    public String getFormattedDate() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Returns the due date of the task.
     *
     * @return The due date of the task as a LocalDateTime object.
     */
    @Override
    public LocalDateTime getDueDate() {
        return this.by;
    }

    /**
     * Converts the deadline task to a format suitable for file storage.
     * This includes the task type, description, and the formatted due date.
     *
     * @return A string representing the deadline task in file storage format.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + by.format(DISPLAY_FORMATTER);
    }

    /**
     * Returns the task type for this deadline task, which is "D" for Deadline.
     *
     * @return The task type string "D".
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns a string representation of the deadline task for display in the CLI.
     * The string includes the task type, description, and the formatted due date.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDate() + ")";
    }
}
