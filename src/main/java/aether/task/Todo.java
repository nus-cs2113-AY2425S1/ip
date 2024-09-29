package aether.task;

/**
 * Todo class representing a simple task with only a description.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toDataString() {
        return "T | " + getStatusForStorage() + " | " + description;
    }
}
