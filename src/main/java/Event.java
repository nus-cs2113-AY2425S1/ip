public class Event extends Task {

    protected String startTime;
    protected String taskEndTime;
    protected final String taskTypeIcon = "[E]";


    public Event(String description, String taskStartTime, String taskEndTime) {
        super(description);
        this.startTime = taskStartTime;
        this.taskEndTime = taskEndTime;
    }

    public String getTaskDescription() {
        return taskTypeIcon + getStatusIcon() + description + " (from: " + startTime + " to: " + taskEndTime + ")";
    }

    @Override
    public String toString() {
        return getTaskDescription();
    }
}
