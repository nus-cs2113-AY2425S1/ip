package tasks;

import poppy.DateParser;

import java.time.LocalDateTime;

/**
 * The {@code Deadline} class represents a deadline task that must be completed by a specified time.
 * It extends the {@code Task} class and specifies the task type.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new {@code Deadline} task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The time by which the task should be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String taskType() {
        return "[D] ";
    }

    /**
     * Returns the deadline by which the task should be completed.
     *
     * @return The deadline time as a string.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String dateTime = DateParser.formatDateTime(by);
        return this.taskType() + "[" + super.getStatusIcon() + "] " + description + " by: " + dateTime;
    }
}
