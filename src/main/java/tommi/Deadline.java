package tommi;

public class Deadline extends Task{

    protected String by;

    public Deadline(boolean isDone, String taskName, String by) {
        super(isDone, taskName);
        this.by = by;
    }

    public Deadline updateIsDone(boolean newIsDone) {
        return new Deadline(newIsDone, this.taskName, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
