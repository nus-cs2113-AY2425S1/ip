package Tasks;

/**
 * Represents an event task.
 * An <code>Event</code> object corresponds to a task represented by a description, a start time and an end time.
 */
public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Creates a new event task with the specified description, start time and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time of the event.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
    
    /**
     * Retrieves the prefix of the task type.
     *
     * @return The prefix of the task type.
     */
    @Override
    public String getPrefix(){
        return "[E]" + super.getPrefix();
    }

}
