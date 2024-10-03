package conglo.task;

/**
 * Represents a deadline task with a description and a deadline date.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    /**
     * Returns a formatted string of the details for this task.
     *
     * @return A string representing the deadline for the task.
     */
    protected String getFormattedDetails() {
        return "by " + by;
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %s | %s | %s",
                getTaskType(),
                getStatusIcon(),
                getDescription(),
                getFormattedDetails());
    }
}
