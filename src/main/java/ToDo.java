public class ToDo extends Task {
    public ToDo(String description) {
        super(TaskType.TODO, description);
    }

    public ToDo(String description, boolean isDone) {
        super(TaskType.TODO, description, isDone);
    }
}
