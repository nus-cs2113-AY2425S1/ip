package erika.task;

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X":" ", description, deadline);
    }

    @Override
    public String generateFileLine() {
        return String.format("D,%s,%s,%s\n", isDone ? "1" : "0", description, deadline);
    }
}
