/**
 * Represents a basic to-do task without any specific deadline or time.
 * Inherits from the Task class.
 */
public class Todo extends Task {

    // Constructs a Todo task with the specified description.
    public Todo(String description) {
        super(description);
    }

    // Converts the to-do task details into a format suitable for saving to a file.
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    // Returns a string representation of the to-do task.
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
