package jeff.task;

import jeff.exception.InvalidFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task in the task management system.
 * The <code>Event</code> class extends the <code>Task</code> class to provide
 * specific functionalities related to event tasks, including the start and end times.
 */
public class Event extends Task{
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) throws InvalidFormatException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Event date inputs are not formatted correctly...");
        }
    }

    public String fileDate(LocalDate field) {
        return field.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String userDate(LocalDate field) {
        return field.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of the event task's content for file storage.
     * This includes a prefix "E" followed by the task's status number, description,
     * start time, and end time.
     *
     * @return A string formatted for file storage, indicating the task type as "E".
     */
    @Override
    public String fileContent() {
        return "E" + super.fileContent() + " | " + fileDate(from) + " | " + fileDate(to);
    }

    /**
     * Returns a string representation of the event task.
     * This includes a prefix "[E]" to indicate the task type as an event task,
     * followed by the task's status icon, description, start time, and end time.
     *
     * @return A string that represents the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + userDate(from) + " to: " + userDate(to) + ")";
    }
}
