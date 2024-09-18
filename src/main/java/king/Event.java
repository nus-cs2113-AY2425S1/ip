package king;

public class Event extends Task {

    protected String taskStartTime;
    protected String taskEndTime;
    protected final String TASKTYPEICON = "[E]";


    public Event(String description, String taskStartTime, String taskEndTime) {
        super(description);
        this.taskStartTime = taskStartTime;
        this.taskEndTime = taskEndTime;
    }

    protected String getTaskDescription() {
        return TASKTYPEICON + getStatusIcon() + description + " (from: " + taskStartTime + " to: " + taskEndTime + ")";
    }

    @Override
    public String toString() {
        return getTaskDescription();
    }
}
