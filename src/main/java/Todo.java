/**
 * Represents a to-do task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a To-do task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the To-do task.
     * The format includes a label for to-dos and the task's completion status and description.
     *
     * @return A string representation of the To-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
