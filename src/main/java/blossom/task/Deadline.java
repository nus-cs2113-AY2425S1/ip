package blossom.task;

/**
 * Represents a deadline-type task with a specific due date.
 * Extends the {@link Task} class with an additional attribute to hold the deadline date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Sets the due date of the deadline task.
     *
     * @param by The new due date to set for the task.
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Retrieves the due date of the deadline task.
     *
     * @return The due date as a string.
     */
    public String getBy() {
        return by;
    }

    /**
     * {@inheritDoc}
     * Adds prefix "D" to indicate the task type, and includes due date.
     *
     * @return The string representation of the task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "D" + super.toFileFormat() + " | " + by;
    }

    /**
     * {@inheritDoc}
     * Includes the task type, description from the superclass, and the due date.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
