package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static constants.Regex.DISPLAY_DATE_FORMAT;
import static constants.Regex.INPUT_DATE_FORMAT;

public class Deadline extends Task {
    // private String by;
    private LocalDate by;
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern(DISPLAY_DATE_FORMAT);
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);

    public Deadline(String taskName, LocalDate by) {
        super(taskName);
        this.by = by;
    }

    public void setBy(LocalDate by) {
        this.by = by;
    }

    public String getByAsString() {
        return displayFormatter.format(by);
    }

    public String getByAsCommand() {
        return saveFormatter.format(by);
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getByAsString());
    }

    @Override
    public String getTaskAsCommand() {
        return String.format("deadline %s /by %s", getTaskName(), getByAsCommand());
    }
}
