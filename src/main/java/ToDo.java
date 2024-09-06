public class ToDo extends Task {
    // private final String TYPE = "T";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
