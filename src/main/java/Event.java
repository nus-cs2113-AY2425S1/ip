/**
 * Represents an event task with a description, start time, and end time.
 * This class extends the {@code Task} class and adds functionality to create an event
 * task with specified start and end times, and represent it as a string.
 */
public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
