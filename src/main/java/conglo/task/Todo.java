package conglo.task;

/**
 * Represents a to-do task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a To-do task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    protected String getTaskType() {
        return "T";
    }

    @Override
    protected String getFormattedDetails() {
        return "";
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %s | %s",
                getTaskType(),
                getStatusIcon(),
                getDescription());
    }
}
