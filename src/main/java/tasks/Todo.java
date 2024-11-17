package tasks;

/**
 * The Todo class represents a simple task without any specific deadline or event time.
 * It extends the Task class and only requires a task description.
 */

public class Todo extends Task{

    public Todo(String task) {
        super(task);
    }

    @Override
    public String getStatusIcon() {
        return ("[T]" + (getIsDone() ? "[X]" : "[ ]"));
    }

}
