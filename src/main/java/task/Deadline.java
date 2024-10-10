package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, which is a task that needs to be done
 * before a specific date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description A description of the task.
     * @param by          The deadline by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the task information formatted for saving to a file.
     *
     * @return The Deadline task as a formatted string suitable for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + "[" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + "]" + ")";
    }
}