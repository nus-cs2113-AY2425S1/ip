package tasks;

public class Todo extends Task{

    public Todo(String task) {
        super(task);
    }

    @Override
    public String getStatusIcon() {
        return ("[T]" + (getIsDone() ? "[X]" : "[ ]"));
    }

}
