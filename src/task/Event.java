package task;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String taskName, boolean isMarked, String from, String to) {
        super(taskName, isMarked);
        this.from = from;
        this.to = to;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isMarked()? "1":"0") + " | " + getTaskName() + " | " + getFrom() + " | " + getTo();
    }
}