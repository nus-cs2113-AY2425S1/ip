package luke.tasks;

public class ToDo extends Task {
    public ToDo() {
        super();
    }

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
