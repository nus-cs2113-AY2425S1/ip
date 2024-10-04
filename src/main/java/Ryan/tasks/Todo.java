package Ryan.tasks;

public class Todo extends Task {

    public static final String TODO_TASK_TYPE = "T";
    public static final String TODO_TASK_ICON = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return TODO_TASK_TYPE;
    }

    @Override
    public String toString() {
        return TODO_TASK_ICON + super.toString();
    }
}
