package model;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private String at; // The event date
    private String from; // Start time
    private String to; // End time

    /**
     * Constructs an Event task with the specified description, status, and timing.
     *
     * @param description the description of the event task
     * @param isDone     the status of the task, true if done, false otherwise
     * @param from       the start time of the event
     * @param to         the end time of the event
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "[E][" + (isDone() ? "X" : " ") + "] " + getDescription() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the save format of the Event task for persistence.
     *
     * @return the save format string
     */
    public String saveFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }
}
