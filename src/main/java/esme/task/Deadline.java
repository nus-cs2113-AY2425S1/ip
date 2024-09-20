package esme.task;

/**
 * A Deadline task is a type of Task that has a deadline by which it must be completed.
 */
public class Deadline extends Task {
    private String taskType;
    private String by;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline by which the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "deadline";
    }

    /**
     * Returns the deadline by which the task must be completed.
     *
     * @return The deadline of the Deadline task.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns the type of the task, which is "deadline" in this case.
     *
     * @return The type of the task, which is "deadline" in this case.
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Returns a string representation of the Deadline task, which is in the format of
     * "[D][] <description> (by: <deadline>)".
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
