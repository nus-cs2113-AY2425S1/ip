package bron.task;

/**
 * Represents a ToDo task. A ToDo task has a description and a status indicating whether it is done or not.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo task with the given description and completion status.
     *
     * @param description The description of the ToDo task.
     * @param isDone Whether the task is done.
     */
    public ToDo(String description, Boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the task type for ToDo.
     *
     * @return "T" representing a ToDo task.
     */
    @Override
    protected String getTaskType() {
        return "T";  // Task type for ToDo
    }
}
