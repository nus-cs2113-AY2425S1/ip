package appal.task;

public class ToDo extends Task {
    protected static final String command = "todo";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getTaskInfo() {
        return command + ", " + super.getTaskInfo();
    }
}