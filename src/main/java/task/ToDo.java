package task;

/**
 * Represent to-do task.
 */
public class ToDo extends Task {

    /**
     * Construct ToDo task with defined description.
     * @param description description of todo task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
