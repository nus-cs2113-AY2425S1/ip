package apsea.task;

/**
 * Represents an event task
 * <code>from</code> represents the start date and time of the task.
 * <code>to</code> represents the end date and time of the task.
 */

public class Event extends Task{
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /* Constructor with completion status */
    public Event(String description, boolean isDone, String from, String to) {
        super(description);
        this.isDone = isDone;
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString()
                    + "[from: " + from + ", to: " + to + "]";
    }

    @Override
    public String toFile() {
        return "E" + "; " + super.toFile() + "; " + from + "; " + to;
    }
}
