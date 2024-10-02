public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        // Initialize a Deadline task with a description and a due time
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        // Return a string representation of the Deadline task
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
