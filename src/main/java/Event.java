public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description, null); // No direct time field; we'll use from and to
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + from + " to: " + to;
    }
}