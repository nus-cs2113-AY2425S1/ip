package bob.task;

/**
 * Represents a deadline task with a specific due date.
 */
public class Deadline extends Task {
    private final String byDeadline;

    /**
     * Constructs a Deadline task object with the given description and deadline.
     *
     * @param description The description of the task.
     * @param deadline    The deadline for completing the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.byDeadline = deadline;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return A string representing the deadline of the task.
     */
    public String getDeadline() {
        return this.byDeadline;
    }
  
    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string that includes the type, description and deadline of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDeadline + ")";
    }
}