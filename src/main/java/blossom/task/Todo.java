package blossom.task;

/**
 * Represents a to-do task without any date or time constraints.
 * This class extends {@link Task} with new string representations for a to-do task.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo instance with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     * Includes the task type and description from the superclass
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     * Adds prefix "T" and includes superclass file format
     *
     * @return The file format string of the to-do task.
     */
    @Override
    public String toFileFormat() {
        return "T" + super.toFileFormat();
    }
}