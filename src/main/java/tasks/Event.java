package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String COMMAND_FORMAT = "event %s /from %s /to %s";
    private LocalDate from;
    private LocalDate to;
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Event(String taskName, LocalDate from, LocalDate to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public String getFromAsString() {
        return displayFormatter.format(from);
    }

    public String getToAsString() {
        return displayFormatter.format(to);
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public String getFromAsCommand() {
        return saveFormatter.format(from);
    }

    public String getToAsCommand() {
        return saveFormatter.format(to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), getFromAsString(), getToAsString());
    }

    @Override
    public String getTaskAsCommand() {
        return String.format(COMMAND_FORMAT, getTaskName(), getFromAsCommand(), getToAsCommand());
    }
}
