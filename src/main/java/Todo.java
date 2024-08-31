public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    //override to string in task
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }
}