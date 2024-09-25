package model;

public class Deadline extends Task {
    private String by; // The deadline date

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + (isDone() ? "X" : " ") + "] " + getDescription() + " (by: " + by + ")";
    }

    public String saveFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by;
    }
}
