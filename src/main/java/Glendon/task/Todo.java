package Glendon.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(int completed, String description) {
        super(description);
        if (completed == 1) {
            super.isCompleted = true;
        }
    }

    @Override
    public String saveToFile() {
        return "T|" + super.saveToFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
