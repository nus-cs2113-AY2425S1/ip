package task.type;

import task.Task;

public class Deadline extends Task {
    public Deadline(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
