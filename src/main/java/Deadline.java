public class Deadline extends Task{
    protected String doneBy;

    public Deadline(String description, String doneBy) {
        super(description);
        setDoneBy(doneBy.replaceFirst("by ", ""));
    }

    public void setDoneBy(String doneBy) {
        this.doneBy = doneBy;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public String printLine() {
        return "[D]" + super.printLine() + " (by: " + doneBy + ")";
    }
}
