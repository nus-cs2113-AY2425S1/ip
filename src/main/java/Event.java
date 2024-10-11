/**
 * contains information of event task inherited form Task
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * constructor of Event
     *
     * @param description task description
     * @param from task starting time
     * @param to task ending time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * override the toString function in Task to have different format when Event task is converted to string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
