package task;

public class Todo extends Task {

    public Todo(String taskName, boolean isMarked) {
        super(taskName, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isMarked()? "1":"0") + " | " + getTaskName();
    }
}
