package quinn.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task in the Quinn task management application.
 * A deadline task is a task with a specific due date and time.
 */
public class Deadline extends Task {
    /** The due date and time of the deadline task as a string. */
    private final String dueDateTime;

    /** The parsed LocalDateTime object of the due date and time. */
    private final LocalDateTime parsedDueDateTime;

    /**
     * Constructs a new Deadline task with the given description and due date/time.
     *
     * @param description the description of the task
     * @param dueDateTime the due date and time of the task in the format "yyyy-MM-dd HHmm"
     */
    public Deadline(String description, String dueDateTime) {
        this(description, dueDateTime, false);
    }

    /**
     * Constructs a new Deadline task with the given description, due date/time, and completion status.
     *
     * @param description the description of the task
     * @param dueDateTime the due date and time of the task in the format "yyyy-MM-dd HHmm"
     * @param isDone the completion status of the task
     */
    public Deadline(String description, String dueDateTime, boolean isDone) {
        super(TaskType.DEADLINE, description, isDone);
        this.dueDateTime = dueDateTime;
        this.parsedDueDateTime = parseDateTime(dueDateTime); // parse dueDateTime into a LocalDateTime object
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
     * Returns a string representation of the Deadline task.
     *
     * @return a string representation of the task, including its description and due date/time
     */
    @Override
    public String toString() {
        if (parsedDueDateTime != null) {
            return super.toString() + " (by: " + formatDateTime(parsedDueDateTime) + ")";
        } else {
            return super.toString() + " (by: " + dueDateTime + ")";
        }
    }

    /**
     * Returns a string representation of the Deadline task suitable for saving to a file.
     *
     * @return a string representation of the task for file storage
     */
    public String saveFormat() {
        if (parsedDueDateTime != null) {
            return super.saveFormat() + " | " + formatDateTime(parsedDueDateTime);
        } else {
            return super.saveFormat() + " | " + dueDateTime;
        }
    }
}
