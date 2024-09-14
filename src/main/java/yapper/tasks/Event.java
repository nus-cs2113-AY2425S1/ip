package yapper.tasks;

import yapper.io.StringStorage;

public class Event extends Task {
    // Additional Attributes
    protected String startDate;
    protected String endDate;

    // Constructor
    public Event(String taskDesc, String startDate, String endDate) {
        super(taskDesc);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // toString Method
    @Override
    public String taskToString() {
        return StringStorage.EVENT_PREFIX
                + super.taskToString()
                + ", from " + startDate + " to " + endDate;
    }
}
