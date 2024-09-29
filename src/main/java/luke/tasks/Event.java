package luke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event() {
        super();
    }
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter toStringFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String toStr = to.format(toStringFormatter);
        String fromStr = from.format(toStringFormatter);
        return String.format("[E][%s] %s (from: %s to: %s)", this.isDone ? "X" : " ", this.description, fromStr, toStr);
    }
    public String toSaveString() {
        DateTimeFormatter toStringFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String toStr = to.format(toStringFormatter);
        String fromStr = from.format(toStringFormatter);
        return String.format("E|%d|%s|%s|%s", isDone ? 1 : 0, description, fromStr, toStr);
    }
}
