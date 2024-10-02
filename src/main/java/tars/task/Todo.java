package tars.task;

public class Todo extends Task {

    // Default constructor
    public Todo() {}

    // Constructor: accepts only description
    public Todo(String description) {
        super(description);  // Call the Task constructor
    }

    // Constructor: accepts description and isDone
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;  // Initialize isDone
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
