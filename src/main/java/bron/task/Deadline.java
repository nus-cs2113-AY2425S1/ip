package bron.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. A deadline has a description, a status, and a due date.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline with the given description and due date.
     *
     * @param description The description of the task.
     * @param by The due date and time of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline with the given description, due date, and completion status.
     *
     * @param description The description of the task.
     * @param by The due date and time of the deadline.
     * @param isDone Whether the task is done.
     */
    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Returns the due date formatted for display purposes.
     *
     * @return A formatted string of the due date.
     */
    public String getFormattedBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return by.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedBy() + ")";
    }

    /**
     * Returns the task in a format suitable for saving to a file.
     *
     * @return A string representing the task in save format.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }

    /**
     * Returns the task type for Deadline.
     *
     * @return "D" representing a Deadline task.
     */
    @Override
    protected String getTaskType() {
        return "D";
    }

}
