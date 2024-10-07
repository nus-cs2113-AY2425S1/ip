package hsien.task;

/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task{
    protected String byDate;

    /**
     * Constructs a Deadline with the specified description and due date.
     *
     * @param description the description of the deadline task
     * @param byDate      the due date for the deadline task
     */
    public Deadline(String description, String byDate){
        super(description);
        this.byDate = byDate;
    }

    /**
     * Retrieves the due date of the deadline task.
     *
     * @return the due date of the deadline task
     */
    public String getByDate() {
        return byDate;
    }

    /**
     * Returns a string representation of the deadline's status and details.
     * The status indicates whether the deadline is marked as complete.
     *
     * @return a formatted string of the deadline's status, description, and due date
     */
    @Override
    public String getStatusDescription() {
        String status = isMarked ? "X" : " ";
        return String.format("[D] [%s] %s (by: %s)", status, super.getDescription(), getByDate());
    }
}
