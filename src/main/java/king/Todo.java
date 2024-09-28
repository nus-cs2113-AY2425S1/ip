package king;

/**
 * Represents a todo task which only has a description.
 */
public class Todo extends Task {

    protected final String TASKTYPEICON = "[T]";

    /**
     * Constructs a todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    protected Todo(String description) {
        super(description);
    }

    /**
     * Returns the description for todo task.
     */
    protected String getTaskDescription() {
        return TASKTYPEICON + getStatusIcon() + description;
    }

    @Override
    public String toString() {
        return getTaskDescription();
    }
}
