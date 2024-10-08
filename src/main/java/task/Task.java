package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static final DateTimeFormatter ALTERNATE_FORMATTER = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
    public static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public LocalDateTime getDueDate() {
        return LocalDateTime.of(1970, 1, 1, 0, 0);
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
