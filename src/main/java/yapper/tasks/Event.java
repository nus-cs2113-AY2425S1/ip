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

    /// Task Conversion Operations
    @Override
    public String taskToString() {
        return "E" + " | " + super.taskToString() + " | " + startDate + " | " + endDate;
    }
    @Override
    public static Event stringToTask(String taskAsString) {
        String[] parts = taskAsString.split(" \\| ");
        String isDone = parts[1];
        String taskDesc = parts[2];
        String startDate = parts[3];
        String endDate = parts[4];
        Event event = new Event(taskDesc, startDate, endDate);
        event.setDoneStatus( isDone.equals("X") );
        return event;
    }
}
