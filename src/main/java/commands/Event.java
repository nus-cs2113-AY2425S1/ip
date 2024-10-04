package commands;

/**
 * Represents Event tasks
 * Inherits description from Task class but include a "from" and
 * "to" parameters
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Event Constructor
     *
     * @param description A string containing the task description
     * @param from A string containing the task start time
     * @param to A string containing the task end time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    /**
     * Returns the event's status of completion.
     * [E] represents deadline
     * @return A string containing the event symbol with the status icon
     */
    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    /**
     * Returns a formatted event for display in task list
     * @return A string containing the task status icon, description,
     * event start and end times, formatted for the task list.
     */
    @Override
    public String createTaskList() {
        return  getStatusIcon() + " "+  description + "(from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a formatted event for saving in the txt file
     * @return A string containing the task status icon, description,
     * event start and end times, formatted saving.
     */
    @Override
    public String createTaskTxt() {
        return "E | " + super.getStatus() + " | " + description + " | " + from + "-" + to;
    }
}
