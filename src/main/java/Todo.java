public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.icon = "T";
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString();
    }
}