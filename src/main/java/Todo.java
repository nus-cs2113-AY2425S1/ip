public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description
     *
     * @param description The task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task
     *
     * @return A string representing the Todo task with its status icon.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
