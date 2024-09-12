package CassHelpers.Tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String taskName, String from,String to) {
        super(taskName);
        setFrom(from);
        setTo(to);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to " + to + ")";
    }
}
