package erika.task;

import erika.settings.Settings;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected String deadline = null;
    protected LocalDate deadlineDate = null;
    protected LocalDateTime deadlineDateTime = null;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public Deadline(String description, LocalDateTime deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X":" ", description,
                (deadlineDateTime != null) ? deadlineDateTime.format(Settings.DATE_TIME_OUT_FORMATTER) : (deadlineDate != null) ?
                        deadlineDate.format(Settings.DATE_OUT_FORMATTER) : deadline);
    }

    @Override
    public String generateFileLine() {
        return String.format("D,%s,%s,%s\n", isDone ? "1" : "0", description, (deadlineDateTime != null) ?
                deadlineDateTime.format(Settings.DATE_TIME_IN_FORMATTER) : (deadlineDate != null) ?
                deadlineDate.format(Settings.DATE_IN_FORMATTER) : deadline);
    }
}
