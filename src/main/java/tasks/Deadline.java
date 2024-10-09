package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class inherits from {@code Task} and represents tasks with deadlines
 */
public class Deadline extends Task {
    private final LocalDate deadline;
    /**
     * Default constructor for new Tasks
     * @param task the task description
     * @param deadline is the deadline of the task
     */
    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }

    /**
     * Constructor used when decoding tasks from storage.
     * @param task the task description
     * @param isDone the task status
     * @param deadline is the deadline of the task
     */
    public Deadline(String task, boolean isDone, LocalDate deadline) {
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
    public String toFileFormat() {
        return String.format("%s | %b | %s | %s", getTypeMarker(), this.isDone, this.task, this.deadline);
    }
}
