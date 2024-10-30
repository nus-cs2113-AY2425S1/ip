import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Class for event objects.
 */
public class Event extends Task{
    protected LocalDate fromDate;

    protected LocalDate toDate;

    protected String fromString;

    protected String toString;

    /**
     * Builder class for event object.
     *
     * @param description The description of the event task.
     * @param from The start date of the event in string format.
     * @param to The end date of the event in string format.
     */
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

    /**
     * Return the string format for the current event.
     *
     * @return the string format of the event task.
     */
    @Override
    public String toString() {
        String displayFrom = (fromDate != null) ? fromDate.toString() : fromString;
        String displayTo = (toDate != null) ? toDate.toString() : toString;
        return "[E]" + currentStatus() + " " + description + " (from: " + displayFrom + " to: " + displayTo + ")";

    }

    /**
     * Return the to be saved format for the current event.
     *
     * @return the save format of the event task.
     */
    @Override
    public String toSave() {
        return "E |" + super.toSave() + " | " + fromString + " | " + toString;
    }

}