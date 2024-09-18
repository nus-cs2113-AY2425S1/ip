public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public void setBy(String by) {
        this.by = by;
    }
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + currentStatus() + " " + description + " (by: " + by + ")";
    }

    @Override
    public String toSave() {
        return "D |" + super.toSave() + " | " + by;
    }

}