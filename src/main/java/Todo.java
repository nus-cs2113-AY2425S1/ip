public class Todo extends Task {

    public Todo(int id, String description) {
        super(id, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
