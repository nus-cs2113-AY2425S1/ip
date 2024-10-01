/**
 * The ToDo class represents a task without any specific time constraints.
 * It extends the Task class and provides a simple to-do task.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with a given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task.
     * The format is [T] followed by the task's status and description.
     *
     * @return A string in the format [T][status] description.
     */
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
