package cristiano.goals;

public class Event extends Goal {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    public static Event fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        String description = parts[2];
        String from = parts[3];
        String to = parts[4];
        Event event = new Event(description, from, to);
        if (parts[1].equals("1")) {
            event.isDone = true;
        }
        return event;
    }
}
