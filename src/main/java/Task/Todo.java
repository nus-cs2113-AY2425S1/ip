package Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFile(String taskDescription, char status) {
        int endIndex = 6;
        return taskDescription.charAt(1) + "|" + status + "|" + taskDescription.substring(endIndex);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}