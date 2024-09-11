package Tars.Task;

public class Deadline extends Task {

    protected String by; // Tars.Task.Deadline

    public Deadline() {}

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}