package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a specific start and end date.
 *
 * This class extends the Task class, encapsulating the task description,
 * and includes additional details such as a start date (from) and an end date (to).
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for the Event task.
     * Attempts to parse the provided from and to date strings using the default date format.
     * If the default format fails, it tries the alternate format.
     *
     * @param description The description of the event.
     * @param from        The start date/time of the event in String format.
     * @param to          The end date/time of the event in String format.
     * @throws IllegalArgumentException If both default and alternate date parsing fail.
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, DEFAULT_FORMATTER);
            this.to = LocalDateTime.parse(to, DEFAULT_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                this.from = LocalDateTime.parse(from, ALTERNATE_FORMATTER);
                this.to = LocalDateTime.parse(to, ALTERNATE_FORMATTER);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Invalid date format. Please provide a valid date in the required format.");
            }
        }
    }

    /**
     * Returns the due date of the event, which is the end date/time (to).
     *
     * @return The end date/time of the event.
     */
    @Override
    public LocalDateTime getDueDate() {
        return this.to;
    }

    /**
     * Converts the event to a format suitable for file storage.
     * This includes the task type, description, and the formatted start and end dates.
     *
     * @return A string representing the event in file storage format.
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + from.format(DEFAULT_FORMATTER) + " | " + to.format(DEFAULT_FORMATTER);
    }

    /**
     * Returns the task type for this event, which is "E" for Event.
     *
     * @return The task type string "E".
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns a string representation of the event for display in the CLI.
     * The string includes the task type, description, and the formatted start and end dates.
     *
     * @return A formatted string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DISPLAY_FORMATTER) + " to: " + to.format(DISPLAY_FORMATTER) + ")";
    }
}
