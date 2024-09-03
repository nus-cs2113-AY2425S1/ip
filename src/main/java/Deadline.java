/**
 * Represents a deadline task with a due date.
 */
public class Deadline extends Task{
    protected final String by;

    /**
     * Constructs a Deadline task.
     *
     * @param name Name of the deadline.
     * @param by Date when the deadline is due.
     */
    public Deadline(String name, String by) {
        super(name);
        this.type = "D";
        this.by = by;
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
}
