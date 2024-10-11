package task;

/**
 * Represents a ToDo task.
 * A ToDo is a task without a specific date or time.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description the description of the ToDo task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the ToDo task,
     * including the type of task and its description.
     *
     * @return a string in the format [T][status] description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string format of the ToDo task for saving to a file.
     * The format is: T | status | description
     *
     * @return a string for file storage
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }
}
