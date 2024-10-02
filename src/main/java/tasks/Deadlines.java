package tasks;

public class Deadlines extends Task {
    private String deadline;
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadlines(String task, boolean isDone, String deadline) {
        super(task, isDone);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }
    public String getTypeMarker() {
        return "[D]";
    }
    @Override
    public void print() {
        System.out.print(getTypeMarker());
        System.out.print(getDoneMarker() + " " + this.task);
        System.out.printf(" (by: %s)\n", this.deadline);
    }
    @Override
    public String toString() {
        return String.format("%s | %b | %s | %s)", getTypeMarker(), this.isDone, this.task, this.deadline);
    }
}
