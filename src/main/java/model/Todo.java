package model;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T][" + (isDone() ? "X" : " ") + "] " + getDescription();
    }

    public String saveFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
}
