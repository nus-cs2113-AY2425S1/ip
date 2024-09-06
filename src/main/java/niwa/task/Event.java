package niwa.task;

/**
 * Represents an event task with a description, a start day, and an end day.
 * This class extends the niwa.task.Task class and specifies the type and short notation for event tasks.
 */
public class Event extends Task {
    /** niwa.task.Event information **/
    protected String fromDay; // The start day of the event
    protected String toDay;   // The end day of the event

    /**
     * Constructs an event niwa.task.Task with the specified description, start day, and end day.
     * The task is marked as undone by default. The type and short notation are set specifically for event tasks.
     *
     * @param description The description of the event
     * @param fromDay The day when the event starts
     * @param toDay The day when the event ends
     */
    public Event(String description, String fromDay, String toDay) {
        super(description);  // Initialize the description in the superclass (niwa.task.Task)
        this.fromDay = fromDay; // Set the start day of the event
        this.toDay = toDay;     // Set the end day of the event
        type = "event";        // Set the task type to "event"
        shortType = "E";       // Set the short notation for event tasks
    }

    // Getter for the start day of the event
    public String getFromDay() {
        return fromDay;
    }

    // Setter for the start day of the event
    public void setFromDay(String fromDay) {
        this.fromDay = fromDay;
    }

    // Getter for the end day of the event
    public String getToDay() {
        return toDay;
    }

    // Setter for the end day of the event
    public void setToDay(String toDay) {
        this.toDay = toDay;
    }

    /**
     * Returns a string containing the full information about the event in a formatted manner.
     * The format is: "[shortType][statusIcon] description (from: fromDay to: toDay)".
     *
     * @return A formatted string containing the event's full information
     */
    @Override
    public String getFullInfo() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                getShortType(), getStatusIcon(), getDescription(), getFromDay(), getToDay());
    }
}
