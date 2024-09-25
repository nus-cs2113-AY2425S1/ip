package aegis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {

    protected Object from; 
    protected Object to;

    public Event(String description, Object from, Object to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toFileFormat() {
        String fromStr = (from instanceof LocalDateTime) ? 
                ((LocalDateTime) from).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.ENGLISH)) : from.toString();
        String toStr = (to instanceof LocalDateTime) ? 
                ((LocalDateTime) to).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.ENGLISH)) : to.toString();
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + fromStr + " | " + toStr;
    }

    @Override
    public String toString() {
        String fromStr = (from instanceof LocalDateTime) ? 
                ((LocalDateTime) from).format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a", Locale.ENGLISH)) : from.toString();
        String toStr = (to instanceof LocalDateTime) ? 
                ((LocalDateTime) to).format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a", Locale.ENGLISH)) : to.toString();
        return "[E]" + super.toString() + " (from: " + fromStr + ", to: " + toStr + ")";
    }
}
