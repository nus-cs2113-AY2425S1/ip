public class Todo extends Task {

    protected final String taskTypeIcon = "[T]";

    public Todo(String description) {
        super(description);
    }

    public String getTaskDescription() {
        return taskTypeIcon + getStatusIcon() + description;
    }

    @Override
    public String toString() {
        return getTaskDescription();
    }
}
