/**
 * Represents a Todo task in the task list. A Todo task only has a description
 * without a specific due date or time.
 */
public class Todo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task, including its description.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task, including its description in file format.
     *
     * @return A string representation of the ToDo task in file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }
}
