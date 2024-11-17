package task;

/**
 * Represents a to-do task.
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo task.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string in the format "[T][X] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}