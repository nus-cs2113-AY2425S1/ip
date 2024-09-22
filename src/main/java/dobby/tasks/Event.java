package dobby.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String fromTime;
    protected String toTime;
    protected LocalDate fromDate;
    protected LocalDate toDate;
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    public Event(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;

        try {
            fromDateTime = LocalDateTime.parse(fromTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            try {
                fromDate = LocalDate.parse(fromTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e2) {
                this.fromTime = fromTime;
            }
        }

        try {
            toDateTime = LocalDateTime.parse(toTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            try {
                toDate = LocalDate.parse(toTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e2) {
                this.toTime = toTime;
            }
        }
    }

    public String getFromTime() {
        if (fromDateTime != null) {
            return fromDateTime.format(DateTimeFormatter.ofPattern("yyyy MM dd HH.mm"));
        } else if (fromDate != null) {
            return fromDate.format(DateTimeFormatter.ofPattern("yyyy MM"));
        } else {
            return fromTime;
        }
    }

    public String getToTime() {
        if (toDateTime != null) {
            return toDateTime.format(DateTimeFormatter.ofPattern("yyyy MM dd HH.mm"));
        } else if (fromDate != null) {
            return toDate.format(DateTimeFormatter.ofPattern("yyyy MM"));
        } else {
            return toTime;
        }
    }

    @Override
    public String toString() {
        String formattedFromTime;
        String formattedToTime;

        if (fromDateTime != null) {
            formattedFromTime = fromDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH.mm"));
        } else if (fromDate != null) {
            formattedFromTime = fromDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            formattedFromTime = fromTime;
        }

        if (toDateTime != null) {
            formattedToTime = toDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH.mm"));
        } else if (toDate != null) {
            formattedToTime = toDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            formattedToTime = toTime;
        }

        return "[E]" + super.toString() + " (from: " + formattedFromTime + " to: " + formattedToTime + ")";
    }
}
