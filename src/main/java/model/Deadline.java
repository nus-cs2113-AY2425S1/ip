package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description, status, and due date.
     *
     * @param description the description of the deadline task
     * @param isDone     the status of the task, true if done, false otherwise
     * @param by         the due date and time of the task
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D][" + (isDone() ? "X" : " ") + "] " + getDescription() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns the save format of the Deadline task for persistence.
     *
     * @return the save format string
     */
    public String saveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by.format(formatter);
    }
}
