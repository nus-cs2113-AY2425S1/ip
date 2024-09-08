public class Todo extends Task {
    public Todo(String description) throws EmptyArgumentException {
        super(description);

        if (description.isBlank()) {
            throw new EmptyArgumentException("Description cannot be empty");
        }

        this.icon = "T";
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString();
    }
}