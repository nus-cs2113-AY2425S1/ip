package yapper.tasks;

import yapper.io.StringStorage;

public class Deadline extends Task {
    // Additional Attributes
    protected String endDate;

    // Constructor
    public Deadline(String taskDesc,
                    String endDate) {
        super(taskDesc);
        this.endDate = endDate;
    }
    public Deadline(String taskDesc, boolean isDone,
                    String endDate) {
        super(taskDesc);
        this.isDone = isDone;
        this.endDate = endDate;
    }
    // No Getters and Setters Yet

    // Task Print Operations for Adding/Removing Data
    @Override
    public String taskToDisplay() {
        return "[" + StringStorage.DEADLINE_SYMBOL + "]"
                + super.taskToDisplay() + ", by " + endDate;
    }

    // Task Conversion Operations
    @Override
    public String taskToString() {
        return StringStorage.DEADLINE_SYMBOL
                + StringStorage.STORAGE_DELIMITER + super.taskToString()
                + StringStorage.STORAGE_DELIMITER + endDate;
    }
}
