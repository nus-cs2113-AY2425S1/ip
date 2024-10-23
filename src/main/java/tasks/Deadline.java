package tasks;

/**
 * The {@code Deadline} class represents a task that has a specific due date or deadline.
 * It extends the abstract {@code Task} class, adding functionality to handle deadlines.
 */
public class Deadline extends Task {

    static final String typeIcon = "[D]";

    String by;

    /**
     * Constructs a new {@code Deadline} task with the specified name and due date.
     *
     * @param newName the name or description of the deadline task.
     * @param newDeadline the due date of the deadline task.
     */
    public Deadline(String newName, String newDeadline) {
        super(newName);
        this.by = newDeadline;
    }

    @Override
    public String getBy() {
        return by;
    }

    public void setBy(String newBy) {
        this.by = newBy;
    }

    @Override
    public String getTypeIcon() {
        return typeIcon;
    }

    /**
     * A placeholder method for tasks that are events. Since deadlines do not have start times,
     * this method returns {@code null}.
     *
     * @return {@code null}, as deadlines do not have a start time.
     */
    @Override
    public String getEventStart() {
        return null;
    }
    /**
     * A placeholder method for tasks that are events. Since deadlines do not have end times,
     * this method returns {@code null}.
     *
     * @return {@code null}, as deadlines do not have an end time.
     */
    @Override
    public String getEventEnd() {
        return null;
    }

    @Override
    public String getTaskDetails() {
        return " (By: " + by + ")";
    }

}
