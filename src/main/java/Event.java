public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getTypeMarker() {
        return "[E]";
    }

    @Override
    public void print() {
        System.out.print(getTypeMarker());
        System.out.print(getDoneMarker() + " " + this.task);
        System.out.printf(" (from: %s to: %s)\n", this.from, this.to);
    }
}
