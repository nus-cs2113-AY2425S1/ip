/**
 * Represents a Todo task in the KBot application.
 * A Todo task is a simple task that does not have a specific time associated with it.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description, null); // No time for Todo
    }

    /**
     * Converts the Todo task to a string format for file storage.
     * The format is: T | isDone | description
     *
     * @return A string representation of the Todo task for saving to a file.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}
