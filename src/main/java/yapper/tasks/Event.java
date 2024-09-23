package yapper.tasks;

import yapper.io.StringStorage;

/**
 * Event is a Task with both a start date and end date.
 */
public class Event extends Task {
    // Additional Attributes
    protected String startDate;
    protected String endDate;

    // Constructor
    public Event(String taskDesc,
                 String startDate, String endDate) {
        super(taskDesc);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Event(String taskDesc, boolean isDone,
                 String startDate, String endDate) {
        super(taskDesc);
        this.isDone = isDone;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    // No Getters and Setters Yet


   /**
     * Converts the event task to a string format for display,
     * including the Event symbol, start date and an end date.
     *
     * @return a formatted string showing the Deadline task's
     * status, description, start date and end date.
     */
    @Override
    public String taskToDisplay() {
        return "[" + StringStorage.EVENT_SYMBOL + "]"
                + super.taskToDisplay() + ", from " + startDate + " to " + endDate;
    }
    /**
     * Converts the event task to a string format for writing to / reading from a file,
     * including the Event symbol, start date and an end date.
     *
     * @return a formatted string representing the deadline task's
     * status, description, start date and end date.
     */
    @Override
    public String taskToString() {
        return StringStorage.EVENT_SYMBOL + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + super.taskToString() + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + startDate + " "
                + StringStorage.COMBINE_USING_DELIMITER + " "
                + endDate;
    }
}
