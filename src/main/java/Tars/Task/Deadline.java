package Tars.Task;

public class Deadline extends Task {
    protected String by; // Save the deadline

    // Constructor: accepts only task description and deadline
    public Deadline(String description, String by) {
        super(description);  // Call the parent Task constructor with the description
        this.by = by;  // Save the deadline
    }

    // Constructor: accepts task description, deadline, and task status
    public Deadline(String description, String by, boolean isDone) {
        super(description);  // Call the parent Task constructor with the description
        this.by = by;  // Save the deadline
        this.isDone = isDone;  // Initialize task status
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
