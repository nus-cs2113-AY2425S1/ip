public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getfrom() {
        return this.from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + 
                String.format(" (from: %s to: %s)", from, to);
    } 
}
