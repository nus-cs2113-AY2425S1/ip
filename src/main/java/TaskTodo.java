public class TaskTodo extends Task {
    public TaskTodo(String taskDesc) {
        super(taskDesc);
    }
    @Override
    public String taskToString() {
        return "[TD]" + super.taskToString();
    }
}
