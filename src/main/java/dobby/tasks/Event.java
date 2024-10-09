package dobby.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDate fromDate = null;
    protected LocalDate toDate = null;
    protected LocalDateTime fromDateTime = null;
    protected LocalDateTime toDateTime = null;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

        parseFromTime(from);
        parseToTime(to);
    }

    private void parseToTime(String toTime) {
        try {
            toDateTime = LocalDateTime.parse(toTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            try {
                toDate = LocalDate.parse(toTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e2) {
                this.to = toTime;
            }
        }
    }

    private void parseFromTime(String fromTime) {
        try {
            fromDateTime = LocalDateTime.parse(fromTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            try {
                fromDate = LocalDate.parse(fromTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e2) {
                this.from = fromTime;
            }
        }
    }

    public String getFrom() {
        if (fromDateTime != null) {
            return fromDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else if (fromDate != null) {
            return fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return from;
        }
    }

    public String getTo() {
        if (toDateTime != null) {
            return toDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else if (toDate != null) {
            return toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return to;
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
            formattedFromTime = from;
        }

        if (toDateTime != null) {
            formattedToTime = toDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH.mm"));
        } else if (toDate != null) {
            formattedToTime = toDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            formattedToTime = to;
        }

        return "[E]" + super.toString() + " (from: " + formattedFromTime + " to: " + formattedToTime + ")";
    }
}
