package bob.task;

public class Deadline extends Task {
    private final String byDeadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.byDeadline = deadline;
    }

    public String getDeadline() {
        return this.byDeadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDeadline + ")";
    }
}
