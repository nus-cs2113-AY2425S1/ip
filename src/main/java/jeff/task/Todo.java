package jeff.task;

/**
 * Represents a todo task
 * The <code>Todo</code> class extends the <code>Task</code> class to provide
 * specific functionalities related to todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task's content for file storage.
     * This includes a prefix "T" followed by the task's status number and description.
     *
     * @return A string formatted for file storage, indicating the task type as "T".
     */
    @Override
    public String fileContent() {
        return "T" + super.fileContent();
    }

    /**
     * Returns a string representation of the todo task.
     * This includes a prefix "[T]" to indicate the task type as a todo task,
     * followed by the task's status icon and description.
     *
     * @return A string that represents the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

