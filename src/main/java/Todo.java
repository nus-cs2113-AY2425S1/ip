/**
 * Class for todos.
 */
public class Todo extends Task {

    /**
     * Builder method for the current todo.
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Return the string format for the current todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Return the to be saved format for the current todo.
     */
    @Override
    public String toSave() {
        return "T |" + super.toSave() + " | ";
    }
}