package yapper.tasks;

import yapper.io.StringStorage;

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

    // Task Print Operations for Adding/Removing Data
    @Override
    public String taskToDisplay() {
        return "[" + StringStorage.EVENT_SYMBOL + "]"
                + super.taskToDisplay() + ", from " + startDate + " to " + endDate;
    }
    // Task Conversion Operations for Saving/Loading Data
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
