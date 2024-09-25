package model;

public class Event extends Task {
    private String at; // The event date
    private String from; // Start time
    private String to; // End time

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E][" + (isDone() ? "X" : " ") + "] " + getDescription() + " (from: " + from + " to: " + to + ")";
    }

    public String saveFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }
}
