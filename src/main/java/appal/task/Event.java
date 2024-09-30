package appal.task;

public class Event extends Task {
    protected static final String command = "event";
    protected String from;
    protected String to;

    /**
     * Class Constructor.
     *
     * @param description Description of Event Task.
     * @param from Start time of event.
     * @param to End time of event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of a EVent task printed in the task list,
     * with "[E]" indicating an Event, indication of completion and event details.
     *
     * @return String representation Event Task for printing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    /**
     * Returns String representation of an Event Task when stored and saved in a text file,
     * including command type, task description and additional information based of type of task.
     *
     * @return String representation of an Event Task for saving.
     */
    @Override
    public String getTaskInfo() {
        return command + ", " + super.getTaskInfo() + ", " + from + ", " + to;
    }
}