public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        // Initialize an Event task with a description, start time, and end time
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        // Return a string representation of the Event task
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
