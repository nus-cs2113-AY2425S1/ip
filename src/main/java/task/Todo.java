package task;

/**
 * Represents a todo task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task, including its completion status
     * and description.
     *
     * @return A formatted string representation of the todo task.
     */
    @Override
    public String toString() {
        return ("[T][" + getDoneStatusIcon() + "] " + description);
    }

    /**
     * Returns a formatted string representation of the todo task for storage.
     *
     * @return A formatted string representing the todo task suitable for saving to a file.
     */
    @Override
    public String formattedTask() {
        return ("T | " + getDoneStatusIcon() + " | " + description);
    }
}

