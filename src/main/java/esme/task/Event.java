package esme.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private String taskType;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = "event";
    }

    public LocalDate getLocalDateFrom() {
        return this.from;
    }

    public LocalDate getLocalDateTo() {
        return this.to;
    }

    public String getDateFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getDateTo() {
        return this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDateFrom() + ", to: " + getDateTo() + ")";
    }
}
