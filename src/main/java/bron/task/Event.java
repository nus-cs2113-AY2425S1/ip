package bron.task;

/**
 * Represents an Event task. An Event task has a description, start time, end time, and a status indicating whether it is done or not.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event with the given description, start time, end time, and completion status.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @param isDone Whether the task is done.
     */
    public Event(String description, String from, String to, Boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the task in a format suitable for saving to a file.
     *
     * @return A string representing the task in save format.
     */
    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + from + " | " + to;
    }

    /**
     * Returns the task type for Event.
     *
     * @return "E" representing an Event task.
     */
    @Override
    protected String getTaskType() {
        return "E";
    }
}
