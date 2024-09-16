package luke.tasks;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event() {
        super();
    }
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }
    public Event(String description, String from, String to) {
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

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", this.isDone ? "X" : " ", this.description, from, to);
    }
    public String toString2() {
        return String.format("E|%d|%s|%s|%s", isDone ? 1 : 0, description, from, to);
    }
}
