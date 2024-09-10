package quinn.task;

public class Event extends Task {
    private final String fromDateTimeInput;
    private final String toDateTimeInput;

    public Event(String description, String fromDateTimeInput, String toDateTimeInput) {
        this(description, fromDateTimeInput, toDateTimeInput, false);
    }

    public Event(String description, String fromDateTimeInput, String toDateTimeInput, boolean isDone) {
        super(TaskType.EVENT, description, isDone);
        this.fromDateTimeInput = fromDateTimeInput;
        this.toDateTimeInput = toDateTimeInput;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + fromDateTimeInput + " to: " + toDateTimeInput + ")";
    }
}
