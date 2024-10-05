package XiaoMe.task;

/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task with a description and a due date.
     *
     * @param description the description of the task
     * @param by the due date of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the due date of the deadline task.
     *
     * @return the due date as a String
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a String representation of the deadline task,
     * including its status, description, and due date.
     *
     * @return a String representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String saveString() {
        return "D|" + getStatusIcon() + "|" + getDescription() + "|" + getBy() + "\n";
    }
}
