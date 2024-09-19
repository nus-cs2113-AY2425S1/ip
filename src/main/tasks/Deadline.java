package tasks;
public class Deadline extends Task {
    protected String by;

    // Constructor for Deadline
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }
    
    @Override
    public String toString() {
        // Add [D] tag and print deadline
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
