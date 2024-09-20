package esme.task;

public class Todo extends Task {
    private String taskType;

    public Todo(String description) {
        super(description);
        this.taskType = "todo";
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
