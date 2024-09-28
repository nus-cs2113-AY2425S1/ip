package Task;

/**
 * Represents a Deadline task, which is a task with a specified due date or time.
 */
public class Deadline extends Task {

    protected String dueTime;

    /**
     * Constructs a Deadline task with the specified description and due time.
     *
     * @param description The description of the task.
     * @param dueTime The time or date the task is due.
     */
    public Deadline(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    /**
     * Converts the Deadline task details to a specific format for saving to a file.
     *
     * @param taskDescription The description of the task.
     * @param status The status of the task (done or not done).
     * @return The formatted string for file storage.
     */
    @Override
    public String toFile(String taskDescription, char status) {
        int byIndex = taskDescription.indexOf("(by: ");
        return taskDescription.charAt(1) + "|" + status + "|" + taskDescription.substring(6, byIndex-1) + "|" + taskDescription.substring(byIndex+5,taskDescription.length()-1);
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return The string representation of the task, including the due time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueTime + ")";
    }
}