package ran.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    
    public Deadline(boolean isDone, String description, String by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
