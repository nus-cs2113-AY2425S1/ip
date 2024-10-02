package tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String toFormatFile() {
        return "T | " + isDone + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
