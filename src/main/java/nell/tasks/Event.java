package nell.tasks;

import nell.common.DateFormats;

import java.time.LocalDate;
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

    /**
     * Returns true if the given date falls between the from-date and to-date,
     * returns false otherwise.
     *
     * @param date The specified date
     * @return True if date is between the from-date and to-date, false otherwise
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        LocalDate fromDate = this.from.toLocalDate();
        LocalDate toDate = this.to.toLocalDate();

        if (date.isBefore(fromDate)) {
            return false;
        } else if (date.isAfter(toDate)) {
            return false;
        } else {
            return true;
        }
    }
}
