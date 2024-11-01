package tasks;

/**
 * Represents a ToDo task in Bento.
 * A ToDo is a simple task with a name, without any associated deadlines or timeframes.
 */
public class ToDo extends Task {
    private static final String COMMAND_FORMAT = "todo %s";

    /**
     * Constructs a ToDo task with the specified task name.
     *
     * @param taskName The name of the ToDo task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a string representation of the ToDo task.
     * The format includes the task type indicator "[T]" followed by the task's done marker and name.
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns the command string to recreate the ToDo task.
     * This is used for saving the task in a format that can be reloaded later.
     *
     * @return The task as a command string.
     */
    @Override
    public String getTaskAsCommand() {
        return String.format(COMMAND_FORMAT, getTaskName());
    }
}
