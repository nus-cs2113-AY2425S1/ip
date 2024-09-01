public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getNameWithStatus() {
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
