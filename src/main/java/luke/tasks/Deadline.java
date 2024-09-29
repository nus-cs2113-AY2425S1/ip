package luke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    public Deadline() {
        super();
    }


    @Override
    public String toString() {
        DateTimeFormatter toStringFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String byString = by.format(toStringFormatter);
        return String.format("[D][%s] %s (by: %s)", this.isDone ? "X" : " ", this.description, byString);
    }
    public String toSaveString() {
        DateTimeFormatter toStringFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String byString = by.format(toStringFormatter);
        return String.format("D|%d|%s|%s", isDone ? 1 : 0, description, byString);
    }
}
