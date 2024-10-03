package tars.task;

/**
 * Represents a Todo task.
 * A Todo task is a type of task without a specific date or time.
 */
public class Todo extends Task {

    /**
     * Default constructor for Todo.
     */
    public Todo() {}

    /**
     * Creates a Todo task with the given description.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);  // Call the Task constructor
    }

    /**
     * Creates a Todo task with the given description and completion status.
     *
     * @param description Task description.
     * @param isDone Completion status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;  // Initialize isDone
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return String representation with task type and status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the formatted string for saving the Todo task to a file.
     *
     * @return String format for saving the Todo task.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
