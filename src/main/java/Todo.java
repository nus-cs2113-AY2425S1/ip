public class Todo extends Task{
    public Todo (String description) {
        super(description);
    }

    @Override
    public String printString() {
        return String.format("[T][%s] %s ", isDone ? "X":" ", description);
    }
}
