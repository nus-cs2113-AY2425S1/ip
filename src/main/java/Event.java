public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String taskName, boolean isMarked, String from, String to) {
        super(taskName, false);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}