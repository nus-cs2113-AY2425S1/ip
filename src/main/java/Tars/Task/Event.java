package Tars.Task;

public class Event extends Task {

    protected String from; // Tars.Task.Event start time.
    protected String to; // Tars.Task.Event end time.

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