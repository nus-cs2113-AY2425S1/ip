package melchizedek.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String taskToFile() {
        return "T | " + super.taskToFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
