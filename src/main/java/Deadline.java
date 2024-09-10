public class Deadline extends Task {
    protected String by;

    // Constructors
    public Deadline(String description, String by) throws InsufficientSpaceException {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
