public class Deadline extends Task{
    protected String doneBy;

    public Deadline(String description) {
        super(description);
        final int DONE_BY_INDEX = 1;

        String[] components = description.split("/");
        setDoneBy(components[DONE_BY_INDEX].replaceFirst("by ", ""));
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
