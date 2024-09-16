package luke.tasks;

public class ToDo extends Task {
    public ToDo() {
        super();
    }

    public ToDo(String description) {
        super(description);
    }
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.description);
    }
    public String toString2() {
        return String.format("T|%d|%s", isDone ? 1 : 0, description);
    }
}
