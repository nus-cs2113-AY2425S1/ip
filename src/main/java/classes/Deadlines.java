package classes;

public class Deadlines extends Task {
    private String deadline;
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadlines(String input) {
        super(input.substring(input.indexOf(" ") + 1, input.indexOf("/by") - 1));
        this.deadline = input.substring(input.indexOf("/by") + 4);
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
        return String.format("%s%s %s (by: %s)", getTypeMarker(), getDoneMarker(), this.task, this.deadline);
    }
}
