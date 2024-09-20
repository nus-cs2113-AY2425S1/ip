public class Event extends Task {
    String from;
    String to;

    public Event(String task, String from, String to) {
        super(task);
        this.from = from.trim();
        this.to = to.trim();
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
