package erika.task;
/**
 * Represents an event task
 * Inherits Task class
 */
public class Event extends Task{
    protected String start;
    protected String end;
    /**
     * @param description <code>String</code> that represents the description of the event
     * @param start <code>String</code> textual representation of the start time
     * @param end <code>String</code> textual representation of the end time
     */
    public Event(String description, String start, String end){
        super(description);
        this.start = start;
        this.end = end;
    }
    /**
     * Overrides the <code>toString</code> method and prints out the deadline in a specific format for printing to console
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", isDone ? "X":" ", description, start, end);
    }
    /**
     * Overrides the <code>toString</code> method and prints out the deadline in a specific format for printing to console
     */
    @Override
    public String generateFileLine() {
        return String.format("E,%s,%s,%s,%s\n", isDone ? "1" : "0", description, start, end);
    }
}
