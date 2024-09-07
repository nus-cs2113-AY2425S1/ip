package task.type;

import task.Task;

public class Event extends Task {
    public Event(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
