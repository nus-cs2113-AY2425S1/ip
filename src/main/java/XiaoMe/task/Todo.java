package XiaoMe.task;

/**
 * Represents a Todo task, which is a type of Task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the Todo task, including
     * its type and status.
     *
     * @return a String representation of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String saveString() {
        return "T|" + getStatusIcon() + "|" + getDescription() + "\n";
    }
}
