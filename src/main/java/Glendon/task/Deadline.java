package Glendon.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(int completed, String description, String by) {
        super(description);
        this.by = by;
        if (completed == 1) {
            super.isCompleted = true;
        }
    }

    @Override
    public String saveToFile() {
        return "D|" + super.saveToFile() + "|by: " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
