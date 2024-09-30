package Tasks;

/**
 * Represents a todo task.
 * A <code>Todo</code> object corresponds to a task represented by a description.
 */
public class Todo extends Task{

    /**
     * Creates a new todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Retrieves the prefix of the task type.
     *
     * @return The prefix of the task type.
     */
    @Override
    public String getPrefix(){
        return "[T]" + super.getPrefix();
    }
}
