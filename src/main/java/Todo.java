public class Todo extends Task {

    protected static final String symbol = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return symbol + super.toString();
    }
}
