package esme.task;

/**
 * Represents a Todo task, which is a task with no deadline and no time span.
 */
public class Todo extends Task {
    private String taskType;

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
        this.taskType = "todo";
    }

    /**
     * Returns a string representation of the Todo task, which is in the format of
     * "[T][] <description>".
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
