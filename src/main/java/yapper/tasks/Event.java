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

    /// Task Conversion Operations
    @Override
    public String taskToString() {
        return StringStorage.EVENT_SYMBOL
                + StringStorage.STORAGE_DELIMITER + super.taskToString()
                + StringStorage.STORAGE_DELIMITER + startDate
                + StringStorage.STORAGE_DELIMITER + endDate;
    }
    @Override
    public static Event stringToTask(String taskAsString) {
        String[] taskParts = StringStorage.splitByDelimiter(taskAsString);
        Task task = Task.stringToTask(taskParts[0] + " | " + taskParts[1]);
        String startDate = taskParts[2];
        String endDate = taskParts[3];
        return new Event(task.taskDesc, task.isDone, startDate, endDate);
    }
}
