/**
 * Represents a task of type "Todo".
 * A Todo task only has a description with no specific time or deadline.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the Todo task.
     */
    Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task, including its type and status.
     *
     * @return A string in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}