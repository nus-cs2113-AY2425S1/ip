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

    public Event (String isDone, String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        if (isDone.equals("X")) {
            this.isDone = true;
        }
    }

    public String getTaskInfo() {
        return "[E][" + this.getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
    }

    public String getTaskStorageInfo() {
        return "E" + DIVIDER + this.getStatusIcon() + DIVIDER + description + DIVIDER + from + DIVIDER + to;
    }

}
