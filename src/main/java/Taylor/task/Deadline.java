package Taylor.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, boolean isCompleted, String by) {
        super(description, isCompleted);
        this.by = by;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String write(){
        return "D | " + (isCompleted? "1": "0") + " | " + description + " | " + by;
    }
}
