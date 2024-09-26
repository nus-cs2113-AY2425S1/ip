/**
 * The Event class represents a task that occurs at a specific time interval.
 * It extends the Task class to provide specific functionality for event tasks.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event object with the specified description, start time, and end time.
     *
     * @param description the description of the event task
     * @param from the starting time of the event
     * @param to the ending time of the event
     */
    public Event(String description, String from, String to) { //constructor for event.
        super(description); //invokes the superclass constructor
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task, including its status, description, and time interval.
     *
     * @return a formatted string representing the event task
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the event task in a format suitable for saving to a file.
     *
     * @return a formatted string representing the event task for saving
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}