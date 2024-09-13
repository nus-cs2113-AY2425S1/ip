package king;

public class Event extends Task {

    protected String startTime;
    protected String taskEndTime;
    protected final String TASKTYPEICON = "[E]";


    public Event(String description, String taskStartTime, String taskEndTime) {
        super(description);
        this.startTime = taskStartTime;
        this.taskEndTime = taskEndTime;
    }

    protected String getTaskDescription() {
        return TASKTYPEICON + getStatusIcon() + description + " (from: " + startTime + " to: " + taskEndTime + ")";
    }

    @Override
    public String toString() {
        return getTaskDescription();
    }
}
