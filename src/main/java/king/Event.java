package king;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 * The task includes a description and the time range during which the event occurs.
 */
public class Event extends Task {

    protected String taskStartTime;
    protected String taskEndTime;
    protected final String TASKTYPEICON = "[E]";
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an event task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param startTime   The starting date and time of the event.
     * @param endTime     The ending date and time of the event.
     */
    protected Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the description for this task.
     */
    protected String getTaskDescription() {
        return TASKTYPEICON + getStatusIcon() + description + " (from: " + taskStartTime + " to: " + taskEndTime + ")";
    }

    /**
     * Returns the start time for this task.
     */
    protected LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time for this task.
     */
    protected LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a MMM dd yyyy");
        return "[E]" + getStatusIcon() + description + " (from: " + startTime.format(formatter) + " to: " + endTime.format(formatter) + ")";
    }
}
