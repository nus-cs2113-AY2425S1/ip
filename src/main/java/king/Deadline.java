package king;

public class Deadline extends Task {

    protected final String taskStartTime = "NIL";
    protected String taskEndTime;
    protected final String TASKTYPEICON = "[D]";

    public Deadline(String description, String taskEndTime) {
        super(description);
        this.taskEndTime = taskEndTime;
    }

    @Override
    protected String getTaskDescription() {
        return TASKTYPEICON + getStatusIcon() + description + " (by: " + taskEndTime + ")";
    }

    @Override
    public String toString() {
        return getTaskDescription();
    }
}
