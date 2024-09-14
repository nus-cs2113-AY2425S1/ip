package yapper.tasks;

import yapper.io.StringStorage;

public class Deadline extends Task {
    // Additional Attributes
    protected String endDate;

    // Constructor
    public Deadline(String taskDesc, String endDate) {
        super(taskDesc);
        this.endDate = endDate;
    }

    // toString Method
    @Override
    public String taskToString() {
        return StringStorage.DEADLINE_PREFIX
                + super.taskToString()
                + ", due " + endDate;
    }
}
