package chattycharlie.task;

import chattycharlie.commands.CommandType;

//todo class
public class Todo extends Task {

    public Todo(String description) {
        super(description, CommandType.TODO);
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
