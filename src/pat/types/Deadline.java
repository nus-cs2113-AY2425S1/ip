package types;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTask() {
        return "[D]" + super.getTask() + " (by: " + by + ")";
    }
}
