package tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private LocalDate deadline;
    public Deadlines(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }
    public Deadlines(String task, boolean isDone, LocalDate deadline) {
        super(task, isDone);
        this.deadline = deadline;
    }
    public LocalDate getDeadline() {
        return deadline;
    }
    public String getTypeMarker() {
        return "[D]";
    }
    @Override
    public void print() {
        System.out.print(getTypeMarker());
        System.out.print(getDoneMarker() + " " + this.task);
        System.out.printf(" (by: %s)\n", this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
    @Override
    public String toString() {
        return String.format("%s | %b | %s | %s", getTypeMarker(), this.isDone, this.task, this.deadline);
    }
}
