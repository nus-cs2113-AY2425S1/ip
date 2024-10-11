/**
 * contains information of deadline task inherited form Task
 */
public class Deadline extends Task {
    protected String by;

    /**
     * constructor of Deadline
     *
     * @param description task description
     * @param by task deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * override the toString function in Task to have different format when Deadline task is converted to string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
