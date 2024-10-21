package tasks;

/**
 * The {@code ToDo} class represents a to-do task that needs to be completed.
 * It extends the {@code Task} class and specifies the task type.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String taskType() {
        return "[T] ";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.taskType() + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
