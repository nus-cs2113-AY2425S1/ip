package aether.task;

/**
 * Represents a simple todo task with only a description.
 * <p>
 * The {@code Todo} class extends the {@code Task} class by specifying a task
 * that only requires a description without any associated date or time.
 * It provides methods to serialize the task for storage and to generate a string
 * representation suitable for display.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Serializes the {@code Todo} task into a string format suitable for storage.
     *
     * @return A string representation of the todo task for storage purposes.
     */
    @Override
    public String toDataString() {
        return "T | " + getStatusForStorage() + " | " + description;
    }

    /**
     * Returns a string representation of the {@code Todo} task for display purposes.
     *
     * @return A formatted string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
