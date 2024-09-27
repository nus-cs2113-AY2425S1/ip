package esme.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline task is a type of Task that has a deadline by which it must be completed.
 */
public class Deadline extends Task {
    private String taskType;
    private LocalDate by;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline by which the task must be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.taskType = "deadline";
    }

    public LocalDate getLocalDate() {
        return this.by;
    }

    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getDate() {
        return by.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Returns a string representation of the Deadline task, which is in the format of
     * "[D][] {description} (by: {deadline})".
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate() + ")";
    }
}
