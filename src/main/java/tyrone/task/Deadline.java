package tyrone.task;

/**
 * Class to store information about single Deadline task
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of Deadline task.
     * @param deadline Deadline of Deadline task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return String representation of Deadline task.
     */
    @Override
    public String getNameWithStatus() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + deadline + ")";
    }

    /**
     * Returns a string representation of the Deadline task to record in save file.
     *
     * @return String representation of Deadline task to record in save file.
     */
    @Override
    public String getSaveRecord() {
        return (isDone ? "1" : "0") + " deadline " + description + " /by " + deadline;
    }
}
