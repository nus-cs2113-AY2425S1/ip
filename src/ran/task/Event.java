package ran.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    
    public Event(boolean isDone, String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone; 
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public String dataFileInput() {
        return "E, " + (isDone ? "1, " : "0, ") + description + ", " + from + ", " + to;
    }
}
