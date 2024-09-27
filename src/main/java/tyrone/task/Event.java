package tyrone.task;

/**
 * Class to store information about single Event task
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructor for Event.
     *
     * @param description Description of Event task.
     * @param start Starting time of event task.
     * @param end Ending time of event task.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return String representation of Event task.
     */
    @Override
    public String getNameWithStatus() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription()
                + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns a string representation of the Event task to record in save file.
     *
     * @return String representation of Event task to record in save file.
     */
    @Override
    public String getSaveRecord() {
        return (isDone ? "1" : "0") + " event " + description + " /from " + start + " /to " + end;
    }
}
