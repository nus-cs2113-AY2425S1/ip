package nova.task;

public class Event extends Task {

    String from;
    String to;

    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        printAcknowledgementMessage(getTaskInfo());
    }

    public String getTaskInfo() {
        return "[E][" + this.getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
    }

}
