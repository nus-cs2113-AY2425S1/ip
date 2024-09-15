package erika.task;

public class Todo extends Task{
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", isDone ? "X":" ", description);
    }

    @Override
    public String generateFileLine() {
        return String.format("T,%s,%s\n", isDone ? "1" : "0", description);
    }
}
