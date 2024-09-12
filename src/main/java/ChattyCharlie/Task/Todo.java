package ChattyCharlie.Task;

import ChattyCharlie.CommandType;

//todo class
public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description) {
        super(description, CommandType.TODO);
        isDone = false;
    }
    public void setDone(boolean done) {
        isDone = done;
    }
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
