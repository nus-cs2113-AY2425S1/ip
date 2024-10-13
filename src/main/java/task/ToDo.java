package task;

/**
 * Represents a Todo task which is a task without any specific date or time.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task with the specified description.
     *
     * @param description A description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the task information formatted for saving to a file.
     * <p>
     * This format is suitable for storage in a persistent format.
     *
     * @return A formatted string representing the ToDo task for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the ToDo task.
     * <p>
     * This representation includes the task's status and description, formatted
     * for user visibility.
     *
     * @return A string representing the ToDo task, including its status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}