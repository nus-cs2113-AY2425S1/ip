public class Todo extends Task{
    public Todo() {}

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        String taskStatus = "[T][" + (isDone ? "X" : " ") + "] ";
        return taskStatus + taskName;
    }
}
