package bean.task;

import bean.exceptions.InsufficientSpaceException;

public class Event extends Task {
    private static final String DELIMITER = "||";

    protected String from;
    protected String to;

    //Constructors
    public Event(String description, String from, String to) throws InsufficientSpaceException {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, Boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to " + to + ")";
    }

    @Override
    public String serialise() {
        return super.serialise() + DELIMITER + from + DELIMITER + to;
    }

}
