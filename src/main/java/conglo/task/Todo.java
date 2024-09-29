package conglo.task;

/**
 * Represents a To-do task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a To-do task with the specified description.
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
