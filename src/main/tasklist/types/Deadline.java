package tasklist.types;

public class Deadline extends Task {
    protected String by;

    // Constructor for Deadline
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the due date.
     *
     * @return A string representing the due date.
     */
    public String getBy() {
        return this.by;
    }
    
    /**
     * Returns a string representation of the task.
     * The representation includes the status icon and the task description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        // Add [D] tag and print deadline
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
