package quinn.task;

public class Deadline extends Task {
    private final String dueDateTime;

    public Deadline(String description, String dueDateTime) {
        this(description, dueDateTime, false);
    }

    public Deadline(String description, String dueDateTime, boolean isDone) {
        super(TaskType.DEADLINE, description, isDone);
        this.dueDateTime = dueDateTime;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + dueDateTime + ")";
    }

    public String saveFormat() {
        return super.saveFormat() + " | " + dueDateTime;
    }
}
