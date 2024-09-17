package apsea.task;

public class Deadline extends Task{
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description);
        this.isDone = isDone;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "[by: " + by + "]";
    }

    @Override
    public String toFile() {
        return "D" + "; " + super.toFile() + "; " + by;
    }
}
