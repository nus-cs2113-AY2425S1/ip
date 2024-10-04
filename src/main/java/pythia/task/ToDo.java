package pythia.task;

import pythia.utility.WriteVisitor;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String accept(WriteVisitor visitor) {
        return visitor.visitToDo(this);
    }
}