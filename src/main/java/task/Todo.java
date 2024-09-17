package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T][" + getDoneStatusIcon() + "] " + description);
    }

    @Override
    public String formattedTask() {
        return ("T | " + getDoneStatusIcon() + " | " + description);
    }
}
