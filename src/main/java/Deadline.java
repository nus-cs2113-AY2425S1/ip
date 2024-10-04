/**
 * Represents a deadline task with a description and due date.
 * This class extends the {@code Task} class and adds a due date for the deadline task.
 * It provides functionality to create a deadline task and represent it as a string.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
