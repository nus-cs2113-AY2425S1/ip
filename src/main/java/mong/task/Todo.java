package mong.task;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        if (isCompleted()) {
            return "T | 1 | " + super.toFileFormat();
        }
        return "T | 0 | " + super.toFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
