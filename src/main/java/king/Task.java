package king;

public class Task {

    protected final String taskStartTime = "NIL";
    protected final String taskEndTime = "NIL";
    public static final String TASKTYPEICON = "[ ]";
    protected String description;
    protected boolean isDone;
    private final static String LINE = "____________________________________________________________\n";

    public Task(String description) {
        this.description = description;
    }

    protected String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    protected String getTaskDescription() {
        return getStatusIcon() + description;
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    protected void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getTaskDescription();
    }

    public String getDescription() {
        return description;
    }
}