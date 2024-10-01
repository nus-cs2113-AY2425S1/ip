package appal.task;

import appal.exception.AppalException;
import appal.exception.InvalidDeadlineFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected static final String command = "deadline";
    protected LocalDate by;

    public Deadline(String description, String by) throws AppalException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineFormatException();
        }
    }

    public LocalDate getBy() {
        return by;
    }

    public String getFormattedDate() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getFormattedDate() + ")";
    }

    @Override
    public String getTaskInfo() {
        return command + ", " + super.getTaskInfo() + ", " + by;
    }
}
