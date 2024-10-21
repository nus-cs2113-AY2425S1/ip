package nell.tasks;

import nell.common.DateFormats;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event class in the task list
 */
public class Event extends Task {
    public static final String TASK_TYPE = "E";

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event with a given description, from-date, and to-date
     *
     * @param description The given description
     * @param from The given from-date
     * @param to The given to-date
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TASK_TYPE);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event with the given data
     *
     * @param description The given description
     * @param isDone Whether the task is done
     * @param from The given from-date
     * @param to The given to-date
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
        this.type = TASK_TYPE;
    }

    /**
     * Returns a formatted string of the Event, to be printed in task lists
     *
     * @return A formatted string of the Event
     */
    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                DateFormats.OUTPUT_DATE_FORMAT.format(this.from), DateFormats.OUTPUT_DATE_FORMAT.format(this.to));
    }

    /**
     * Returns a formatted string of the event, to be written to a file
     *
     * @return A formatted string of the Event
     */
    @Override
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
