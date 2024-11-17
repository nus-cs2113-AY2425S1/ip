package archibald.task;

/**
 * Represents a Todo task, which is a specific type of Task.
 * This class extends the Task class and provides additional functionality
 * specific to Todo items.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
