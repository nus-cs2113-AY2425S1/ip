package dobby.tasks;
public class Event extends Task {

    protected String fromTime;
    protected String toTime;

    public Event(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public String getFromTime() {
        return fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromTime + " to: " + toTime + ")";
    }
}
