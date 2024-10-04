package Glendon.task;
import java.time.LocalDate;

public class Deadline extends Task {
    protected String dueTime;

    /**
     * Construct the deadline task with given task description and the due date with default
     * completion status of the task is always set to false
     *
     * @param description the description of the task
     * @param dueTime the due date of the task
     */
    public Deadline(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    /**
     * Constructs the deadline task with the given completion indicator, where 1 indicates that
     * the task is completed and 0 indicates that the task has yet to be completed, given task
     * description and due date
     *
     * @param completionIndicator an integer to show if the task has been completed
     * @param description the description of the task
     * @param dueTime the due date of the task
     */
    public Deadline(int completionIndicator, String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
        if (completionIndicator == 1) {
            super.isCompleted = true;
        }
    }

    /**
     * Returns the String format of saving a deadline task
     *
     * @return the format for saving a deadline task
     */
    @Override
    public String saveToFile() {
        return "D|" + super.saveToFile() + "|" + dueTime;
    }

    /**
     * Returns a string representation of the task type, [D] representing deadline task,
     * task description, completion status of the task and due date.
     *
     * @return the task type, task description, completion status of the task and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dueTime + ")";
    }
}
