package november.tasks;

/**
 * Represents a Deadline task, which is a task that must be completed by a specific date and time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param description Describes the task.
     * @param by The deadline by which the task should be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Prints the confirmation message for an added Deadline task.
     */
    @Override
    public void printTask() {
        System.out.print("[" + getTaskIcon() + "][" + getStatusIcon() + "] " + getDescription());
        System.out.println(" (by:" + by + ")");
    }

    /**
     * Returns the task icon specific to Deadline tasks.
     *
     * @return The string "D" representing a Deadline task.
     */
    @Override
    public String getTaskIcon() {
        return "D";
    }
}
