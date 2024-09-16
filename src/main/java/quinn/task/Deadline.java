package quinn.task;

public class Deadline extends Task {
    private final String byDateTimeInput;

    public Deadline(String description, String byDateTimeInput) {
        this(description, byDateTimeInput, false);
    }

    public Deadline(String description, String byDateTimeInput, boolean isDone) {
        super(TaskType.DEADLINE, description, isDone);
        this.byDateTimeInput = byDateTimeInput;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + byDateTimeInput + ")";
    }

    public String saveFormat() {
        return super.saveFormat() + " | " + byDateTimeInput;
    }
}
