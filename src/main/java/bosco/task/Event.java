package bosco.task;

import bosco.parser.DateTimeParser;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return DateTimeParser.formatDateTime(from);
    }

    public String getTo() {
        return DateTimeParser.formatDateTime(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
