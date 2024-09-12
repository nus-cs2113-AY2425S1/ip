package tasks;

public class ToDo extends Task {
    private static final String COMMAND_FORMAT = "todo %s";

    public ToDo (String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String getTaskAsCommand() {
        return String.format(COMMAND_FORMAT, getTaskName());
    }
}
