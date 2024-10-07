package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, formatter);
    }

    public String getFormattedDate() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + by.format(formatter);
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
