package Task;

/**
 * Represents a Todo task, which is a basic task with no additional time or date.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo task details to a specific format for saving to a file.
     *
     * @param taskDescription The description of the task.
     * @param status The status of the task (done or not done).
     * @return The formatted string for file storage.
     */
    @Override
    public String toFile(String taskDescription, char status) {
        int endIndex = 6;
        return taskDescription.charAt(1) + "|" + status + "|" + taskDescription.substring(endIndex);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}