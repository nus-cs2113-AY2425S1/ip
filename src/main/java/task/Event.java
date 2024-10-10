package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task, which is a task that starts and ends
 * at specific dates/times.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Creates a new Event task with the given description, start, and end times.
     *
     * @param description A description of the event.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the task information formatted for saving to a file.
     *
     * @return The Event task as a formatted string suitable for saving to a file,
     *         including its status, description, start time, and end time.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " +
                end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representing the Event task, including its status,
     *         description, start time, and end time in a readable format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                "[" + start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + "]" +
                " to: " +
                "[" + end.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + "]" + ")";
    }
}
