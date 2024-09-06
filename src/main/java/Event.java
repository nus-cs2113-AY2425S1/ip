public class Event extends Task {

    protected String eventFrom;
    protected String eventTo;
    protected static final String symbol = "[E]";

    public Event(String description, String eventFrom, String eventTo) {
        super(description);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    @Override
    public String toString() {
        return symbol + " " + super.toString() + "(from: " + eventFrom + " to: " + eventTo + ")";
    }
}
