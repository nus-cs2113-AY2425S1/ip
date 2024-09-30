package CassHelpers.types;

/**
 * The Todo class represents a task of type "Todo"
 * It inherits from the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo object with the specified task name.
     *
     * @param taskName The name of the Todo task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Overrides the default toString method to provide a specific format for Todo tasks.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string format of the Todo task to be written to a file.
     *
     * @return The writable string format of the Todo task.
     */
    public String toWritableString() {
        return "T" + super.toWritableString();
    }
}
