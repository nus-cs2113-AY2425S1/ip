package blossom.task;

/**
 * Represents an event task with a start and end time.
 * This class extends {@link Task} by adding time and date details.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs a new Event with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time as a string.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time as a string.
     */
    public String getTo() {
        return to;
    }

    /**
     * {@inheritDoc}
     * Adds prefix "E" to indicate the task type, and includes the start and end times.
     *
     * @return The file format string of the event task.
     */
    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormat() + " | " + from + " | " + to;
    }

    /**
     * {@inheritDoc}
     * Includes the task type, description from the superclass, and the start and end times.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
