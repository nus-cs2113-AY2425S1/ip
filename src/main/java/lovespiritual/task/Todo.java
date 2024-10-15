package lovespiritual.task;

/**
 * Represents a Todo task, which only has a description.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the specific Todo task, including the type.
     *
     * @return String in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}