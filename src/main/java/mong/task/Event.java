package mong.task;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor of an Event task.
     * @param description The name of the task (e.g. chinese class) in String format.
     * @param from The start date and/or time of the event.
     * @param to The end date and/or time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormat() {
        if (isCompleted()) {
            return "E | 1 | " + super.toFileFormat() + " | " + from + " | " + to;
        }
        return "E | 0 | " + super.toFileFormat() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
