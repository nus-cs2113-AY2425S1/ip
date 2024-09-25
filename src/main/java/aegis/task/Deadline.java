package aegis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {

    protected Object by;

    public Deadline(String description, Object by) {
        super(description);
        this.by = by;
    }

    public String toFileFormat() {
        if (by instanceof LocalDateTime) {
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                    ((LocalDateTime) by).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.ENGLISH));
        }
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        if (by instanceof LocalDateTime) {
            return "[D]" + super.toString() + " (by: " +
                    ((LocalDateTime) by).format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a", Locale.ENGLISH)) + ")";
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
