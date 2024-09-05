public class Event extends Task {

    protected String from; // Event start time.
    protected String to; // Event end time.

    public Event() {}

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";  // Show [E]
    }

}