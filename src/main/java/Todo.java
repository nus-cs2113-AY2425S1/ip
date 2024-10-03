/**
 * Represents a simple todo task without any specific date or time.
 */
public class Todo extends Task{
    /**
     * Constructs a Todo with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }
    /**
     * Returns the string representation of the todo task.
     *
     * @return The formatted string of the todo task.
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
    /**
     * Returns a string representation for saving the todo task to a file.
     *
     * @return A formatted string of the todo task for file storage.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
