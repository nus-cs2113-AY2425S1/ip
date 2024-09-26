package chattycharlie.task;

import chattycharlie.commands.CommandType;

//Events
public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description, CommandType.EVENT);
        this.start = start;
        this.end = end;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStart() {
        return start;
    }


    public void setEnd(String end) {
        this.end = end;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}