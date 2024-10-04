package bob.task;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task{

    /**
     * Constructs a ToDo object with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo (String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task, including its status icon and description.
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
