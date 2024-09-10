package task;

/**
 * Represents a ToDo task, which is a task without any specific date or time.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task with the given description.
     *
     * @param description A description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
