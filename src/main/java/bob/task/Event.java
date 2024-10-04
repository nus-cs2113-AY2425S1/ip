package bob.task;

public class Event extends Task {
    private final String startOfEvent;
    private final String endOfEvent;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startOfEvent = startTime;
        this.endOfEvent= endTime;
    }

    public String getEventStartTime() {
        return this.startOfEvent;
    }

    public String getEventEndTime() {
        return this.endOfEvent;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startOfEvent + " to: " + this.endOfEvent + ")";
    }
}
