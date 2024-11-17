package task.type;

import exceptions.IrisException;
import task.Task;

public class Event extends Task {
    public Event(String text) throws IrisException {
        super(text);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
