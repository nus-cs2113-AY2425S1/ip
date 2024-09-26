package quinn.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task in the Quinn task management application.
 * An event task is a task with a specific start and end date/time.
 */
public class Event extends Task {
    /** The start date and time of the event task as a string. */
    private final String startDateTime;

    /** The end date and time of the event task as a string. */
    private final String endDateTime;

    /** The parsed LocalDateTime object of the start date and time. */
    private final LocalDateTime parsedStartDateTime;

    /** The parsed LocalDateTime object of the end date and time. */
    private final LocalDateTime parsedEndDateTime;


    /**
     * Constructs a new Event task with the given description, start date/time, and end date/time.
     *
     * @param description the description of the task
     * @param startDateTime the start date and time of the event in the format "yyyy-MM-dd HHmm"
     * @param endDateTime the end date and time of the event in the format "yyyy-MM-dd HHmm"
     */
    public Event(String description, String startDateTime, String endDateTime) {
        this(description, startDateTime, endDateTime, false);
    }


    /**
     * Constructs a new Event task with the given description, start date/time, end date/time, and completion status.
     *
     * @param description the description of the task
     * @param startDateTime the start date and time of the event in the format "yyyy-MM-dd HHmm"
     * @param endDateTime the end date and time of the event in the format "yyyy-MM-dd HHmm"
     * @param isDone the completion status of the task
     */
    public Event(String description, String startDateTime, String endDateTime, boolean isDone) {
        super(TaskType.EVENT, description, isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.parsedStartDateTime = parseDateTime(startDateTime); // parse startDateTime into a LocalDateTime object
        this.parsedEndDateTime = parseDateTime(endDateTime); // parse endDateTime into a LocalDateTime object
    }

    /**
     * Parses the input date/time string into a LocalDateTime object.
     *
     * @param inputDateTime the input date/time string in the format "yyyy-MM-dd HHmm"
     * @return the parsed LocalDateTime object, or null if parsing fails
     */
    private LocalDateTime parseDateTime(String inputDateTime) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(inputDateTime, inputFormatter);
        } catch (DateTimeParseException e) {
            return null; // if inputDateTime is not in "yyyy-MM-dd HHmm" format
        }
    }

    /**
     * Formats a LocalDateTime object into a readable string.
     *
     * @param parsedDateTime the LocalDateTime object to format
     * @return a formatted string representation of the date/time
     */
    private String formatDateTime(LocalDateTime parsedDateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return parsedDateTime.format(outputFormatter);
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return a string representation of the task, including its description, start date/time, and end date/time
     */
    @Override
    public String toString() {
        if (parsedStartDateTime != null && parsedEndDateTime != null) {
            return super.toString()
                    + " (from: " + formatDateTime(parsedStartDateTime)
                    + " to: " + formatDateTime(parsedEndDateTime)  + ")";
        } else {
            return super.toString() + " (from: " + startDateTime + " to: " + endDateTime + ")";
        }
    }

    /**
     * Returns a string representation of the Event task suitable for saving to a file.
     *
     * @return a string representation of the task for file storage
     */
    public String saveFormat() {
        if (parsedStartDateTime != null && parsedEndDateTime != null) {
            return super.saveFormat()
                    + " | " + formatDateTime(parsedStartDateTime)
                    + " | " + formatDateTime(parsedEndDateTime);
        } else {
            return super.saveFormat() + " | " + startDateTime + " | " + endDateTime;
        }
    }
}
