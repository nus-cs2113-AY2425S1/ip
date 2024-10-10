package november.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task, which is a task that occurs within a specific time frame.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs a new Event task with the given description, start time, and end time.
     *
     * @param description Describes the task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
    }

    /**
     * Prints the confirmation message for an added Event task.
     */
    @Override
    public void printTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yy");
        String formattedStartDateTime = start.format(formatter);
        String formattedEndDateTime = start.format(formatter);
        System.out.print("[" + getTaskIcon() + "][" + getStatusIcon() + "] " + getDescription());
        System.out.println(" (from: " + formattedStartDateTime + " to: " + formattedEndDateTime + ")");
    }

    /**
     * Returns the task icon specific to Event tasks.
     *
     * @return The string "E" representing an Event task.
     */
    @Override
    public String getTaskIcon() {
        return "E";
    }

    /**
     * Returns a string representation of the Event task for saving to a file.
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        return "event | " + isComplete + " | " + description + " | " + start + " | " + end;
    }
}
