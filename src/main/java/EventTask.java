public class EventTask extends Task {
    private String from;
    private String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    // New method to return event details as a string (from and to times)
    public String getEventDetails() {
        return from + " /to " + to;  // Returns the event details as "from /to to"
    }

    @Override
    public String getType() {
        return "E";  // E for EventTask
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}