public class Event extends Task{
    protected final String start;
    protected final String end;

    /**
     * Constructor for Event
     *
     * @param name Name of the event.
     * @param start Date when the event begins.
     * @param end Date when the event ends.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.type = "E";
        this.start = start;
        this.end = end;
    }

    /**
     * Prints the event and its status in one line.
     * Overwrites toString method in Java Object class.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + (this.isDone ? "X" : " ") + "] " + this.name
                + " (from: " + this.start + " to: " + this.end + ")";
    }
}
