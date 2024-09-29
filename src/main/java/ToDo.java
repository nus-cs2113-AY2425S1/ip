/**
 * The ToDo class represents a task with only a description, without any specific dates.
 * It extends the Task class and adds a simple string representation for ToDo tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     * The string includes a "[T]" to indicate that it is a ToDo task.
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
