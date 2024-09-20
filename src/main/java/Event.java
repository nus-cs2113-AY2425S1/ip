public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) { //constructor for event.
        super(description); //invokes the superclass constructor
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}