public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String printString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X":" ", description, deadline);
    }
}
