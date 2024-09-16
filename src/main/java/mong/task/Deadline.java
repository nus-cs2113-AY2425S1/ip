package mong.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        if (isCompleted()) {
            return "D | 1 | " + super.toFileFormat() + " | " + by;
        }
        return "D | 0 | " + super.toFileFormat() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
