package ran.task;

/**
 * Represents an user-input Event task. A <code>Event</code> object corresponds to a event task represented
 * with the attributes of the description of the event as a string, 
 * the start time of the event as a string,
 * the end time of the even t as a string, 
 * and whether it is done as a boolean value.
 * Inherits from the base Task class.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor of a <code>Event</code> object.
     * Event is initialised as undone.
     * Useful for when user inputs a event task.
     *
     * @param description String representing description given to the event task
     * @param from String representing the start time of the event
     * @param to String representing the end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Alternative constructor of a <code>Event</code> object.
     * Attribute of whether the event task is done can be controlled.
     * Useful for reading previously created event task from a data file.
     *
     * @param isDone Boolean value representing whether the event task has been done
     * @param description String representing description given to the event task
     * @param from String representing the start time of the event
     * @param to String representing the end time of the event
     */
    public Event(boolean isDone, String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone; 
    }

    /**
     * Overload method returning a formatted string of the object.
     *
     * @return String representing the event task according to how it should be displayed
     */
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Represent the event task in the way it is saved to a data file.
     *
     * @return String representing how the event task is saved to a data file
     */
    public String dataFileInput() {
        return "E, " + (isDone ? "1, " : "0, ") + description + ", " + from + ", " + to;
    }
}
