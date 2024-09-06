/**
 * Represents a deadline task with a description and a due date.
 * This class extends the Task class and specifies the type and short notation for deadline tasks.
 */
public class Deadline extends Task {
    /** Deadline information **/
    protected String byDay; // The due date of the deadline task

    /**
     * Constructs a deadline Task with the specified description and due date.
     * The task is marked as undone by default. The type and short notation are set specifically for deadline tasks.
     *
     * @param description The description of the deadline task
     * @param byDay The due date for the deadline task
     */
    public Deadline(String description, String byDay) {
        super(description);  // Initialize the description in the superclass (Task)
        this.byDay = byDay;  // Set the due date for the deadline task
        type = "deadline";   // Set the task type to "deadline"
        shortType = "D";     // Set the short notation for deadline tasks
    }



    // Getter for the due date of the deadline task
    public String getByDay() {
        return byDay;
    }

    // Setter for the due date of the deadline task
    public void setByDay(String byDay) {
        this.byDay = byDay;
    }

    /**
     * Returns a string containing the full information about the deadline in a formatted manner.
     * The format is: "[shortType][statusIcon] description (by: byDay)".
     *
     * @return A formatted string containing the deadline's full information
     */
    @Override
    public String getFullInfo() {
        return String.format("[%s][%s] %s (by: %s)",
                getShortType(), getStatusIcon(), getDescription(), getByDay());
    }
}
