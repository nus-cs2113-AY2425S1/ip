package task.type;

import exceptions.IrisException;
import task.Task;

public class Deadline extends Task {
    public Deadline(String text) throws IrisException {
        super(text);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
