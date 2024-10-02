package akshan.task;

import akshan.handler.DateTime;

/**
 * Represents a deadline task with a due date.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructs a Deadline task.
     *
     * @param name Name of the deadline.
     * @param by Date when the deadline is due.
     * @throws IllegalArgumentException If name or by is null or empty.
     */
    public Deadline(String name, String by) throws IllegalArgumentException {
        super(name);
        if (by == null || by.trim().isEmpty()) {
            throw new IllegalArgumentException("Deadline date cannot be null or empty.");
        }
        this.type = "D";
        this.by = DateTime.convertToString(by);
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + (this.isDone ? "X" : " ") + "] " + this.name
                + " (by: " + this.by + ")";
    }

    /**
     * Returns a string representation of the deadline task suitable to be stored in data file.
     *
     * @return String representation of the deadline task (storage format).
     */
    @Override
    public String toStorageString(String separator) {
        return this.type + separator + (super.isDone ? "1" : "0") + separator + this.name
                + separator + this.by;
    };
}
