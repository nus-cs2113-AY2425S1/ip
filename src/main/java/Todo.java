public class Todo extends Task {
    public Todo(String description) {
        super(description, null); // No time for Todo
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}