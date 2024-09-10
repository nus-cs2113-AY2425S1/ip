package nova.task;

public class Deadline extends Task{

    String by;

    public Deadline (String description, String by) {
        super(description);
        this.by = by;
        printAcknowledgementMessage(getTaskInfo());
    }

    public String getTaskInfo() {
        return "[D][" + this.getStatusIcon() + "] " + description + " (by: " + by + ")";
    }

}
