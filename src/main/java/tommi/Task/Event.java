package tommi.Task;

/**
 * Represents an Event that is a child class of Task. Contains a
 * from string and a to string with the user input time
 * event starts and ends
 */
public class Event extends Task{

    protected String from;
    protected String to;

    public Event(boolean isDone, String taskName, String from, String to) {
        super(isDone, taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a new copy of Event object with updated isDone value
     *
     * @param newIsDone new isDone value
     * @return New copy of Event with updated isDone
     */
    @Override
    public Event updateIsDone(boolean newIsDone) {
        return new Event(newIsDone, this.taskName, this.from, this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to " + this.to + ")";
    }
}
