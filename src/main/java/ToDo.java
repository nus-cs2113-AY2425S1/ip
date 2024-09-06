/**
 * Represents a to-do task with a description and a completion status.
 * This class extends the Task class and specifies the type and short notation for to-do tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs a to-do Task with the specified description.
     * The task is marked as undone by default. The type and short notation are set specifically for to-do tasks.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);  // Initialize the description in the superclass (Task)
        type = "todo";       // Set the task type to "todo"
        shortType = "T";     // Set the short notation for to-do tasks
    }
}
