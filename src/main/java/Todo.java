public class Todo extends Task{
    public Todo() {}

    public Todo(String taskName) {
        super(taskName);
    }

    public Todo(boolean isDone, String formattedTaskInfo) {
        super(isDone, formattedTaskInfo);
    }

    @Override
    public String toString() {
        String taskStatus = "[T][" + (isDone ? "X" : " ") + "] ";
        if (formattedTaskInfo.isBlank()) {
            return taskStatus + taskName;
        }
        return taskStatus + formattedTaskInfo;
    }
}
