/**
 * contains information of todo task inherited form Task
 */
public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    /**
     * override the toString function in Task to have different format when Todo task is converted to string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
