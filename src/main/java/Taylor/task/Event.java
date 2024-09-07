package Taylor.task;

public class Event extends Task{
    String from;
    String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, Boolean isComplete, String from, String to) {
        super(description, isComplete);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public String write(){
        return "E | " + (isCompleted? "1": "0") + " | " + description + " | " + from + "-" + to;
    }
}
