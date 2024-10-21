package bosco.task;

/**
 * Represents a Todo, a subclass of Task.
 */
public class Todo extends Task {
    /**
     * Class constructor.
     *
     * @param description Description of this Todo.
     * @param isDone Whether this Todo is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Overrides the <code>toString</code> method of Task
     * to format the string representation of this Todo.
     *
     * @return Formatted string of this Todo with Todo indicator.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
