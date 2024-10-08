package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific start and end date
 *
 * This class encapsulates a task description and a start date, and an end date
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

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
                LocalDateTime now = LocalDateTime.now();
                this.from = now;
                this.to = now.plusDays(5);
                System.out.println("Using local date as format was wrong");
            }
        }
    }

    @Override
    public LocalDateTime getDueDate() {
        return this.to;
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + from.format(DEFAULT_FORMATTER) + " | " + to.format(DEFAULT_FORMATTER);
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DISPLAY_FORMATTER) + " to: " + to.format(DISPLAY_FORMATTER) + ")";
    }
}
