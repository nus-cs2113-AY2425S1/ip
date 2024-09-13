package esme.task;

public class Event extends Task {
    private String from;
    private String to;
    private String taskType;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = "event";
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }
}
