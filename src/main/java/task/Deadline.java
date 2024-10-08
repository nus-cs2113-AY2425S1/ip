package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DEFAULT_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                this.by = LocalDateTime.parse(by, ALTERNATE_FORMATTER);
            } catch (DateTimeParseException e2) {
                this.by = LocalDateTime.now();
                System.out.println("Invalid date format, using local date");
            }
        }
    }

    public String getFormattedDate() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public LocalDateTime getDueDate() {
        return this.by;
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + by.format(DISPLAY_FORMATTER);
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDate() + ")";
    }
}
