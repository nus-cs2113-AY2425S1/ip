package nell.tasks;

import nell.common.DateFormats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends nell.tasks.Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, "E");
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                DateFormats.OUTPUT_DATE_FORMAT.format(this.from), DateFormats.OUTPUT_DATE_FORMAT.format(this.to));
    }

    public String getFileLine() {
        return String.format("%s|%s|%s", super.getFileLine(), DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(this.from),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(this.to));
    }
}
