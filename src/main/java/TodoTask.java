public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    // Implementing the abstract getType() method from Task
    @Override
    public String getType() {
        return "T";  // T for TodoTask
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();  // Add [T] to the string representation of the task
    }
}