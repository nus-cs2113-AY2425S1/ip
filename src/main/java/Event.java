public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", this.getStatusIcon(),
                this.description, this.from, this.to);
    }
}
