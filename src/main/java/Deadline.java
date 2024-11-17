/**
 * The Deadline class represents a task with a deadline.
 * It extends the Task class to provide specific functionality for deadline tasks.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     *
     * @param description the description of the deadline task
     * @param by the deadline date/time for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task, including its status, description, and deadline.
     *
     * @return a formatted string representing the deadline task
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the deadline task in a format suitable for saving to a file.
     *
     * @return a formatted string representing the deadline task for saving
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}