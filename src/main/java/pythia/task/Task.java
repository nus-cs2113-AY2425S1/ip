package pythia.task;

public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
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
}
