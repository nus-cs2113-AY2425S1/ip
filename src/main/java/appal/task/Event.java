package appal.task;

public class Event extends Task {
    protected static final String command = "event";
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public String getTaskInfo() {
        return command + ", " + super.getTaskInfo() + ", " + from + ", " + to;
    }
}