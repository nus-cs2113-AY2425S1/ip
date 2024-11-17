package task.type;

import exceptions.IrisException;
import task.Task;

public class Todo extends Task {
    public Todo(String text) throws IrisException {
        super(text);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
