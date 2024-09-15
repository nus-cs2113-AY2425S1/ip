package appal.task;

public class Deadline extends Task {
    protected static final String command = "deadline";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String getTaskInfo() {
        return command + ", " + super.getTaskInfo() + ", " + by;
    }
}
