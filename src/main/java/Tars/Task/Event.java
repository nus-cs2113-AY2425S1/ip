package Tars.Task;

public class Event extends Task {
    protected String from; // Save event start time
    protected String to;   // Save event end time

    // Constructor: accepts only task description, start time, and end time
    public Event(String description, String from, String to) {
        super(description);  // Call the parent Task constructor with the description
        this.from = from;  // Save event start time
        this.to = to;  // Save event end time
    }

    // Constructor: accepts task description, start time, end time, and task status
    public Event(String description, String from, String to, boolean isDone) {
        super(description);  // Call the parent Task constructor with the description
        this.from = from;  // Save event start time
        this.to = to;  // Save event end time
        this.isDone = isDone;  // Initialize task status
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
