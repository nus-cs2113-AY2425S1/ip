package task;

public class Deadline extends Task {

    protected String dueDate;

    //task.Deadline Constructor inherits task.Task
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    //Override toString method to show marker
    @Override
    public String getTaskMarker() {
        return "D";
    }

    @Override
    public String toFileFormat() {
        String status;
        if (isDone) {
            status = "1";
        } else {
            status = "0";
        }
        return "D | " + status + " | " + description +  " (by: " + dueDate + ")";
    }
}