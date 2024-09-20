package atom.task;

public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String item, String from, String to) {
        super(item);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public String setTaskType() {
        return "E";
    }
}
