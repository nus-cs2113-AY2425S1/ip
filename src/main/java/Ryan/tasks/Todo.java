package Ryan.tasks;

/**
 * Represents a simple task without a specific time.
 */
public class Todo extends Task {
  
    public static final String TODO_TASK_TYPE = "T";
    public static final String TODO_TASK_ICON = "[T]";
  
  /**
     * Constructs a Todo task with the given description.
     *
     * @param description The task's description.
     */

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the task type, which is "T" for todo tasks.
     *
     * @return A string representing the task type.
     */
    @Override
    public String getTaskType() {
        return TODO_TASK_TYPE;
    }

    /**
     * Returns a string representation of the todo task for display.
     *
     * @return A formatted string representing the todo task.
     */
    @Override
    public String toString() {
        return TODO_TASK_ICON + super.toString();
    }
}
