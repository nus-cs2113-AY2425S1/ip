package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String task, boolean isDone, LocalDate to, LocalDate from) {
        super(task, isDone);
        this.to = to;
        this.from = from;
    }
    public LocalDate getFrom() {
        return from;
    }
    public LocalDate getTo() {
        return to;
    }
    public String getTypeMarker() {
        return "[E]";
    }

    @Override
    public void print() {
        System.out.print(getTypeMarker());
        System.out.print(getDoneMarker() + " " + this.task);
        System.out.printf(" (from: %s to: %s)\n", this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
    @Override
    public String toString() {
        return String.format("%s | %b | %s | %s | %s", getTypeMarker(), this.isDone,
                this.task, this.from, this.to);
    }
}
