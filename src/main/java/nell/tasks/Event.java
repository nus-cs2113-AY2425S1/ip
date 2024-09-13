package nell.tasks;

public class Event extends nell.tasks.Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description, "E");
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                this.from, this.to);
    }
}
