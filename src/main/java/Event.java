import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class represents a task that has a start date (from) and an end date (to).
 * It extends the Task class and includes specific date information.
 */
public class Event extends Task {

    // Start date of the event
    protected LocalDate from;

    // End date of the event
    protected LocalDate to;

    /**
     * Constructs an Event object with a description, start date, and end date.
     * The dates are validated and parsed from the input strings.
     *
     * @param description The description of the event.
     * @param from The start date of the event in "yyyy-MM-dd" format.
     * @param to The end date of the event in "yyyy-MM-dd" format.
     * @throws DukeException If the date format is invalid.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        try {
            this.from = LocalDate.parse(from); // Parse the start date
            this.to = LocalDate.parse(to);     // Parse the end date
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input date in valid format yyyy-MM-dd");
        }
    }

    /**
     * Gets the start date of the event.
     *
     * @return The start date as a string.
     */
    public String getFrom() {
        return from.toString();
    }

    /**
     * Gets the end date of the event.
     *
     * @return The end date as a string.
     */
    public String getTo() {
        return to.toString();
    }

    /**
     * Returns a string representation of the event, including the description, start, and end dates.
     * The dates are formatted in "MMM dd yyyy" format.
     *
     * @return A string representing the event with its description, start, and end dates.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedFrom = from.format(formatter);
        String formattedTo = to.format(formatter);
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}
