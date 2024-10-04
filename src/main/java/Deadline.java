public class Deadline extends Task {
    String by;

    /**
     * Represents a deadline task with a specific due date or time ("by").
     * Extends the Task class to include deadline-specific details.
     *
     * @param task the description of the deadline task
     * @param by   the due date or time of the task
     */
    public Deadline(String task, String by) {
        super(task);
        this.by = by.trim();
    }

    /**
     * Returns the due date or time of the deadline task.
     *
     * @return the due date or time as a string
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline, including the task description
     * and the due date or time in the format "[D] task (by: due date)".
     *
     * @return a formatted string representing the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
