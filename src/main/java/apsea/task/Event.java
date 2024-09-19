package apsea.task;

public class Event extends Task{
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description);
        this.isDone = isDone;
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString()
                    + "[from: " + from + ", to: " + to + "]";
    }

    @Override
    public String toFile() {
        return "E" + "; " + super.toFile() + "; " + from + "; " + to;
    }
}
