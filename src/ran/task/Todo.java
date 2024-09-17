package ran.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String dataFileInput() {
        return "T, " + (isDone ? "1, " : "0, ") + description;
    }
}
