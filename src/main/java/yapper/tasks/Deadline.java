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

    // Task Conversion Operations
    @Override
    public String taskToString() {
        return "D" + " | " + super.taskToString() + " | " + endDate;
    }
    @Override
    public static Deadline stringToTask(String taskAsString) {
        String[] parts = taskAsString.split(" \\| ");
        String isDone = parts[1];
        String taskDesc = parts[2];
        String endDate = parts[3];
        Deadline deadline = new Deadline(taskDesc, endDate);
        deadline.setDoneStatus( isDone.equals("X") );
        return deadline;
    }
}
