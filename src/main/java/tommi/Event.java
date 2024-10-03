package tommi;

public class Event extends Task{

    protected String from;
    protected String to;


    public Event(boolean isDone, String taskName, String from, String to) {
        super(isDone, taskName);
        this.from = from;
        this.to = to;
    }

    public Event updateIsDone(boolean newIsDone) {
        return new Event(newIsDone, this.taskName, this.from, this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.from + "to" + this.to + ")";
    }
}
