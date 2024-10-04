package Ryan.tasks;

public class Deadline extends Task {

    public static final String DEADLINE_TASK_TYPE = "D";
    public static final String DEADLINE_TASK_ICON = "[D]";

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return DEADLINE_TASK_TYPE;
    }

    @Override
    public String toFile() {
        return String.format("%s | %d | %s | %s", getTaskType(), isMarked() ? 1 : 0, getDescription(), by);
    }

    @Override
    public String toString() {
        return DEADLINE_TASK_ICON + super.toString() + " (by: " + by + ")";
    }
}
