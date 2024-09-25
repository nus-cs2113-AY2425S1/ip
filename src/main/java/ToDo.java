/**
 * Represents a todo task.
 * A <code>ToDo</code> object inherits from the <code>Task</code> object.
 * <code>ToDo</code> contains a constant attribute called <code>TYPE</code> which designates 
 * the type of task <code>ToDo</code> is.
 * @see Task
 */
public class ToDo extends Task {
    private final String TYPE = "T";

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String that contains the type of task the <code>ToDo</code> is.
     * @return TYPE
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Allows the typeand description of the task to 
     * be printed instead of just the object's location in memory.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
