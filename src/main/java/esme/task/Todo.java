package esme.task;

/**
 * Represents a Todo task, which is a task with no deadline and no time span.
 */
public class Todo extends Task {
    /**
     * Creates a new Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * The format is: "[T][] {description}".
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
