public class Deadlines extends Task{
    private String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }


    public String getDeadline() {
        return this.deadline;
    }
    public String getTypeMarker() {
        return "[D]";
    }
    public void print() {
        System.out.print(getTypeMarker());
        System.out.print(getDoneMarker() + " " + this.task);
        System.out.printf(" (by: %s)\n", this.deadline);
    }
}
