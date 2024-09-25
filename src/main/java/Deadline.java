/**
 * Represents a task that has a deadline.
 * A <code>Deadline</code> object inherits from the <code>Task</code> object.
 * <code>Deadline</code> contains a constant attribute called <code>TYPE</code> which designates 
 * the type of task <code>Deadline</code> is.
 * <code>Deadline</code> contains a String attribute called <code>by</code> which contains 
 * the time that the <code>Deadline</code> needs to be completed by.
 * @see Task
 */
public class Deadline extends Task {
    private final String TYPE = "D";
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String that contains the type of task the <code>Deadline</code> is.
     * @return TYPE
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Returns a String that contains the deadline of the task.
     * @return deadline
     */
    @Override
    public String getBy() {
        return this.by;
    }
    
    /**
     * Allows the type, description and deadline of the task to be printed instead
     * of just the object's location in memory.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
