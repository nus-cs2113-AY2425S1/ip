package Ryan.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start time and an end time.
 */
public class Event extends Task {

    public static final String EVENT_TASK_TYPE = "E";
    public static final String EVENT_TASK_ICON = "[E]";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    protected LocalDate from;
    protected LocalDate  to;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The event's description.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from, INPUT_FORMATTER);
        this.to = LocalDate.parse(to, INPUT_FORMATTER);
    }

    /**
     * Returns the task type, which is "E" for event tasks.
     *
     * @return A string representing the task type.
     */
    @Override
    public String getTaskType() {
        return EVENT_TASK_TYPE;
    }

    /**
     * Returns a string representation of the event task in a file-friendly format.
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFile() {
        return String.format("%s | %d | %s | %s | %s", getTaskType(), isMarked() ? MARKED_VALUE : UNMARKED_VALUE, getDescription(), from.toString(), to.toString());
    }

    /**
     * Returns a string representation of the event task for display.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return EVENT_TASK_ICON + super.toString() + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }
}
