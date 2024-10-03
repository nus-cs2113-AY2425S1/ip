/**
 * Represents a to-do task with a description.
 * This class extends the {@code Task} class and provides functionality
 * to create a to-do task and represent it as a string.
 */
public class Todo extends Task{

    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
