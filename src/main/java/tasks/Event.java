package tasks;

public class Event extends Task {
    private static final String COMMAND_FORMAT = "event %s /from %s /to %s";
    private String from;
    private String to;

    public Event(String taskName, String from, String to) {
        super(taskName);
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
        return String.format("[E]%s (from: %s to: %s)", super.toString(), getFrom(), getTo());
    }

    @Override
    public String getTaskAsCommand() {
        return String.format(COMMAND_FORMAT, getTaskName(), getFrom(), getTo());
    }
}
