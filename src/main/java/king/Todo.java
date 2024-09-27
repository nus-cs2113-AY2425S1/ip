package king;

public class Todo extends Task {

    protected final String taskStartTime = "NIL";
    protected final String taskEndTime = "NIL";
    protected final String TASKTYPEICON = "[T]";

    public Todo(String description) {
        super(description);
    }

    protected String getTaskDescription() {
        return TASKTYPEICON + getStatusIcon() + description;
    }

    @Override
    public String toString() {
        return getTaskDescription();
    }
}
