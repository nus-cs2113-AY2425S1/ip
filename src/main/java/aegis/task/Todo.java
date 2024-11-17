package aegis.task;

/**
 * The Todo class represents a task of type "Todo".
 * It inherits from the Task class and adds a specific label to differentiate it as a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task, including its type label and status.
     *
     * @return A string that represents the Todo task, prefixed with "[T]".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
