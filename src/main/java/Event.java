public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String input) {
        super(input.split("/", 3)[0].trim());

        String[] parts = input.split("/", 3);

        this.from = parts[1].trim().substring(5); // ignore "from "
        this.to = parts[2].trim().substring(3);   // ifnore "to "
        this.icon = "E";
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
