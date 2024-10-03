package Uranus.Tasks;

import UranusExceptions.UranusExceptions;
import UranusExceptions.EmptyDescriptionException;
import UranusExceptions.InvalidEventException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task with a specific start and end time.
 * Inherits from the Task class and includes additional information about the event duration.
 */
public class Events extends Task{

    protected String from;
    protected String to;
    private static final String EVENT_TAG = "E";
    private static final String SEPARATOR = "/";
    private static final String START_TIME_LABEL = "from";
    private static final String END_TIME_LABEL = "to";
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Constructs a new Event task with the given description.
     * The description should contain the task, start time, and end time in the format:
     * "task description /from start /to end".
     *
     * @param description The task description, which includes the start and end times.
     * @throws UranusExceptions If the description, start time, or end time is invalid.
     */
    public Events(String description) throws UranusExceptions {
        super(description, EVENT_TAG);
    }

    /**
     * Parses the date-time string into a formatted date-time.
     * If the date-time string cannot be parsed, return the original string.
     *
     * @param preParsedDateTime The date-time string to parse.
     * @return The formatted date-time string if parsing is successful, or the original string if parsing fails.
     */
    private String parseDateTime(String preParsedDateTime) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(preParsedDateTime, INPUT_FORMAT);
            return dateTime.format(OUTPUT_FORMAT);
        } catch (DateTimeParseException e) {
            return preParsedDateTime;
        }
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time as a string.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the start time of the event.
     *
     * @param from The start time as a string.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time as a string.
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the end time of the event.
     *
     * @param to The end time as a string.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Sets the task description. The description is split into the task, start time, and end time.
     * If the description, start time, or end time is empty, an InvalidEventException is thrown.
     *
     * @param description The full description string, containing the task, start time, and end time.
     * @throws UranusExceptions If the description, start time, or end time is invalid.
     */
    @Override
    public void setDescription(String description) throws UranusExceptions {
        try {
            String[] str = description.split(SEPARATOR);
            setFrom(parseDateTime(str[1].substring(START_TIME_LABEL.length()).trim()));
            setTo(parseDateTime(str[2].substring(END_TIME_LABEL.length()).trim()));
            this.description = str[0] + "(" + START_TIME_LABEL + ": "
                    + from + " " + END_TIME_LABEL + ": " + to + ")";
            if (str[0].isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new InvalidEventException();
            }
        } catch (Exception e) {
            throw new InvalidEventException();
        }
    }
}
