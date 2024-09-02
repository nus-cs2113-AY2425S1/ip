public class Event  extends Task{
    private final String eventStart;
    private final String eventEnd;

    public Event (String description, String eventStart, String eventEnd){
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), eventStart, eventEnd);
    }
}
