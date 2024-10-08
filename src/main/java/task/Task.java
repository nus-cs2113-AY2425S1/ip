package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static final DateTimeFormatter ALTERNATE_FORMATTER = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
    protected static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toFileFormat() {
        return String.format("%s | %d | %s", getTaskType(), isDone ? 1 : 0, description);
    }

    public String getTaskType() {
        return "T";
    }

    // function override
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
