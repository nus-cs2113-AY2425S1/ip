import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class represents a task that has a specific due date (by).
 * It extends the Task class and adds functionality for handling deadlines.
 */
public class Deadline extends Task {

    // The due date for the task
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     * The date is validated and parsed from the input string.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task in "yyyy-MM-dd" format.
     * @throws DukeException If the date format is invalid.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);

        try {
            this.by = LocalDate.parse(by); // Parse the due date
        } catch (DateTimeParseException e) {
            throw new DukeException("please input date in valid format yyyy/MM/dd");
        }
    }

    /**
     * Gets the due date of the task.
     *
     * @return The due date as a string.
     */
    public String getBy() {
        return by.toString();
    }

    /**
     * Returns a string representation of the deadline task.
     * The string includes a "[D]" to indicate that it is a Deadline task,
     * and the due date is formatted as "MMM dd yyyy".
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedBy = by.format(formatter);

        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}
