package task;

/**
 * The Event class represents an event task.
 * It extends the abstract Task class and provides implementations for formatting the task's display output and file-saving format.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String taskName, boolean isMarked, String from, String to) {
        super(taskName, isMarked);
        this.from = from;
        this.to = to;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     * The string includes the "[E]" , followed by the inherited task's string representation and the event's time range.
     *
     * @return A string that shows the task type, its completion status, name, and event time range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the event task to a format suitable for saving to a file.
     * The format is "E | isMarked | taskName | from | to".
     *
     * @return A string formatted for file storage of the event task.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isMarked()? "1":"0") + " | " + getTaskName() + " | " + getFrom() + " | " + getTo();
    }
}