package hsien.task;

/**
 * Represents a todo task with a description.
 */
public class Todo extends Task{
    /**
     * Represents a todo task with a description.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Returns a string representation of the todo's status and description.
     * The status indicates whether the todo is marked as complete.
     *
     * @return a formatted string of the todo's status and description
     */
    @Override
    public String getStatusDescription() {
        return "[T] " + super.getStatusDescription();
    }
}
