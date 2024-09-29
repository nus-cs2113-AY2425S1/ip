package pythia.task;

import pythia.utility.WriteVisitor;

public class Task implements Savable {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return isDone;
    }

    @Override
    public String toString() {
        if (isDone == false) {
            return "[ ] " + name;
        } else {
            return "[X] " + name;
        }
    }

    @Override
    public String accept(WriteVisitor visitor) {
        return visitor.visitTask(this);
    }
}
