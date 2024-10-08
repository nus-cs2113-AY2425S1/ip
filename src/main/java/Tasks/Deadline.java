package Tasks;
/**
 * Represents a task with a deadline.
 * A <code>Deadline</code> object corresponds to a task represented by a description and a deadline date/time.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Creates a new deadline task with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Retrieves the deadline of the task.
     *
     * @return The deadline of the task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    /**
     * Retrieves the prefix of the task type.
     *
     * @return The prefix of the task type.
     */
    @Override
    public String getPrefix(){
        return "[D]" + super.getPrefix();
    }
}