public class Deadline extends Task {

    protected String by;

    public Deadline(String taskName, boolean isMarked, String by) {
        super(taskName, false);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

