package mong.task;

public class Todo extends Task{

    /**
     * Constructor of a Todo task.
     * @param description The name of the task (e.g. chinese class) in String format.
     */
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
