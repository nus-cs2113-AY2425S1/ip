package Ryan.tasks;

public class Event extends Task {

    public static final String EVENT_TASK_TYPE = "E";
    public static final String EVENT_TASK_ICON = "[E]";

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskType() {
        return EVENT_TASK_TYPE;
    }

    @Override
    public String toFile() {
        return String.format("%s | %d | %s | %s | %s", getTaskType(), isMarked() ? MARKED_VALUE : UNMARKED_VALUE, getDescription(), from, to);
    }

    @Override
    public String toString() {
        return EVENT_TASK_ICON + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
