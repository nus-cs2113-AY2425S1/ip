package nell.tasks;

public class Event extends nell.tasks.Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description, "E");
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                this.from, this.to);
    }

    public String getFileLine() {
        return String.format("%s%s|%s|", super.getFileLine(), this.from, this.to);
    }
}
