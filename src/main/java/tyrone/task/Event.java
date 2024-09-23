package tyrone.task;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getNameWithStatus() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription()
                + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String getSaveRecord() {
        return (isDone ? "1" : "0") + " event " + description + " /from " + start + " /to " + end;
    }
}
