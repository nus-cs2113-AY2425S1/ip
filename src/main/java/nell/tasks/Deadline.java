package nell.tasks;

import nell.common.DateFormats;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public static final String TASK_TYPE = "D";

    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, TASK_TYPE);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
        this.type = TASK_TYPE;
    }

    public String toString() {
        return String.format("%s (by: %s)", super.toString(), DateFormats.OUTPUT_DATE_FORMAT.format(this.by));
    }

    public String getFileLine() {
        return String.format("%s|%s", super.getFileLine(), DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(this.by));
    }

    /**
     * Returns true if the by-date is the same as the given date, returns false otherwise.
     *
     * @param date The specified date
     * @return True if the by-date is the same as the given date, false otherwise
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        LocalDate byDate = by.toLocalDate();
        return date.equals(byDate);
    }
}
