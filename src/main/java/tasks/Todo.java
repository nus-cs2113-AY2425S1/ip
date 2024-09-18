package tasks;

public class Todo extends Task{

    public Todo(String contents) {
        super(contents);
    }

    @Override
    public String getStatusIcon() {
        return ("[T]" + (getIsDone() ? "[X]" : "[ ]"));
    }

}
