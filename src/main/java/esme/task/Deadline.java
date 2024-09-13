package esme.task;

public class Deadline extends Task {
    private String taskType;
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "deadline";
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
