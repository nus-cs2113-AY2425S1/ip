package apsea.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    /* Constructor with completion status*/
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFile() {
        return "T" + "; " + super.toFile();
    }
}
