package task;

import exceptions.InsufficientSpaceException;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event (String description, String from, String to) throws InsufficientSpaceException {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to " + to + ")";
    }

}
