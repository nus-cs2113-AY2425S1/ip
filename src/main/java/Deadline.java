public class Deadline extends Task {

    protected String taskEndTime;
    protected final String taskTypeIcon = "[D]";

    public Deadline(String description, String taskEndTime) {
        super(description);
        this.taskEndTime = taskEndTime;
    }

    @Override
    public String getTaskDescription() {
        return taskTypeIcon + getStatusIcon() + description + " (by: " + taskEndTime + ")";
    }

    @Override
    public String toString() {
        return getTaskDescription();
    }
}
