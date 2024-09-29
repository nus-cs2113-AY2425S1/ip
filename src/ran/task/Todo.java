package ran.task;

/**
 * Represents an user-input Todo task. A <code>Todo</code> object corresponds to a todo task represented
 * with the attributes of the description of the todo as a string, 
 * and whether it is done as a boolean value.
 * Inherits from the base Task class.
 */
public class Todo extends Task {
    /**
     * Constructor of a <code>Todo</code> object.
     * Todo is initialised as undone.
     * Useful for when user inputs a todo task.
     *
     * @param description String representing description given to the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Alternative constructor of a <code>Todo</code> object.
     * Attribute of whether the todo task is done can be controlled.
     * Useful for reading previously created todo task from a data file.
     *
     * @param isDone Boolean value representing whether the todo task has been done
     * @param description String representing description given to the todo task
     */
    public Todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Overload method returning a formatted string of the object.
     *
     * @return String representing the todo task according to how it should be displayed
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Represent the todo task in the way it is saved to a data file.
     *
     * @return String representing how the todo task is saved to a data file
     */
    public String dataFileInput() {
        return "T, " + (isDone ? "1, " : "0, ") + description;
    }
}
