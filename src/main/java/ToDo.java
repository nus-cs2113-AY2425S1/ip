public class ToDo extends Task {
    public ToDo(String description) {
        // Initialize a ToDo task with the given description
        super(description);
    }

    @Override
    public String toString() {
        // Return a string representation of the ToDo task
        return "[T]" + super.toString();
    }
}
