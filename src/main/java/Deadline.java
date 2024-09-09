public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The task description.
     * @param by The deadline by which the task should be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return A string representing the Deadline task with its status icon and due date.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + by + ")";
    }
}
