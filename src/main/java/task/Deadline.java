package task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return ("[D][" + getDoneStatusIcon() + "] " + description + " (by: " + by + ")");
    }

    @Override
    public String formattedTask() {
        return ("D | " + getDoneStatusIcon() + " | " + description + " | " + by);
    }

}
