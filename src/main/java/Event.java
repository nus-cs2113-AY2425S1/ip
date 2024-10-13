import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected LocalDate fromDate;

    protected LocalDate toDate;

    protected String fromString;

    protected String toString;

    public Event(String description, String from, String to) {
        super(description);
        this.fromString = from;
        this.toString = to;
        try {
            this.fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            this.fromDate = null;
            this.toDate = null;
        }
    }

    @Override
    public String toString() {
        String displayFrom = (fromDate != null) ? fromDate.toString() : fromString;
        String displayTo = (toDate != null) ? toDate.toString() : toString;
        return "[E]" + currentStatus() + " " + description + " (from: " + displayFrom + " to: " + displayTo + ")";

    }

    @Override
    public String toSave() {
        return "E |" + super.toSave() + " | " + fromString + " | " + toString;
    }

}