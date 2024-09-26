package quinn.task;

public class Event extends Task {
    private final String startDateTime;
    private final String endDateTime;

    public Event(String description, String startDateTime, String endDateTime) {
        this(description, startDateTime, endDateTime, false);
    }

    public Event(String description, String startDateTime, String endDateTime, boolean isDone) {
        super(TaskType.EVENT, description, isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }

    public String saveFormat() {
        return super.saveFormat() + " | " + startDateTime + " | " + endDateTime;
    }
}
