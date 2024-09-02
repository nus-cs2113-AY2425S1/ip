public class Deadline extends Task {
    protected String by;

    // Constructor for Deadline
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    
    @Override
    public String toString() {
        // Add [D] tag and print deadline
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
