/**
 * Class for todos.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task with the specified descrtiption.
     *
     * @param task the description of the todo task.
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Return the string format for the current todo.
     *
     * @return string format for the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Return the to be saved format for the current todo.
     *
     * @return the to be saved format for the todo task.
     */
    @Override
    public String toSave() {
        return "T |" + super.toSave() + " | ";
    }
}