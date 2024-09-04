public class TaskTodo extends Task {
    public TaskTodo(String taskDesc) {
        super(taskDesc);
    }
    @Override // do I need this?
    public String taskToString() {
        return "[T]" + super.taskToString();
    }
}
