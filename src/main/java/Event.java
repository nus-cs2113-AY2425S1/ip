/**
 * Represents an Event task with a description, start time, and end time.
 */
public class Event extends Task {

    protected String to;
    protected String from;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to){
        super(description);
        this.to = to;
        this.from = from;
    }

    /**
     * Returns a string representation of the Event task, including the start and end times.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the Event task, including the start and end times for file format.
     *
     * @return A string representation of the Event task in file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + from + " to " + to;
    }
}
