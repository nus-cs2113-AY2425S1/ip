public class Event extends Task{
    protected String eventStart;
    protected String eventEnd;

    public Event(String event, String eventStart, String eventEnd) {
        super(event);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart + " to: "
                + eventEnd + ")";

    }
}
