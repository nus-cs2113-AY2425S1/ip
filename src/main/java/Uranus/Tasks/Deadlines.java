package Uranus.Tasks;

import UranusExceptions.UranusExceptions;
import UranusExceptions.EmptyDescriptionException;
import UranusExceptions.InvalidDeadlineException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task with a specific deadline date and time.
 * Inherits from the Task class and includes additional information about the deadline.
 * Has a specific tag "D" for deadlines.
 */
public class Deadlines extends Task {

    protected String by;
    private static final String DEADLINE_TAG = "D";
    private static final String DEADLINE_SEPARATOR = "/by";
    private static final String DEADLINE_LABEL = "by: ";
    private static final DateTimeFormatter DATE_TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter DATE_TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Constructs a new Deadline task with the given description.
     * The description should contain both the task and the deadline in the format:
     * "task description /by deadline".
     *
     * @param description The task description, which includes the deadline.
     * @throws UranusExceptions If the description or deadline is invalid.
     */
    public Deadlines(String description) throws UranusExceptions {
        super(description, DEADLINE_TAG);
    }

    /**
     * Sets the deadline date and time.
     *
     * @param by The deadline date and time as a string.
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Gets the deadline date.
     *
     * @return The deadline date and time as a string.
     */
    public String getBy() {
        return by;
    }

    /**
     * Parses the deadline date and time string into a formatted date and time.
     * If the string cannot be parsed, it returns the original string.
     *
     * @param by The deadline date and time as a string.
     * @return The formatted string if parsing is successful, or the original string if parsing fails.
     */
    private String parseDateTime(String by) {
        try {
            LocalDateTime  dateTime = LocalDateTime.parse(by, DATE_TIME_INPUT_FORMAT);
            return dateTime.format(DATE_TIME_OUTPUT_FORMAT);
        } catch (DateTimeParseException e) {
            return by;
        }
    }

    /**
     * Sets the task description. The description is split into the task and the deadline.
     * If the description or deadline is invalid, an InvalidDeadlineException is thrown.
     *
     * @param description The full description string, containing the task and the deadline separated by "/by".
     * @throws UranusExceptions If the description or deadline format is invalid.
     */
    @Override
    public void setDescription(String description) throws UranusExceptions {
        try {
            String[] str = description.split(DEADLINE_SEPARATOR);
            setBy(parseDateTime(str[1].trim()));
            this.description = str[0] + "(" + DEADLINE_LABEL + getBy() + ")";
            if (str[0].isEmpty() || by.isEmpty()) {
                throw new InvalidDeadlineException();
            }
        } catch (Exception e) {
            throw new InvalidDeadlineException();
        }
    }
}
